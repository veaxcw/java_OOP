package com.chengw.utils;

import com.alibaba.fastjson.JSON;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.chengw.utils.String2JSON.writeToJson;

/**
 * Excel 转Json
 * @author chengw
 */
public class Excel2JSON {

    private static final String PATH_BASE = "E:/材料做法表 文档/00-内容库 v1.0";

    private static  List<Map<String,Object>> result = new LinkedList<>();

    public static final String EXCEL2003 = "xls";

    public static final String EXCEL2007 = "xlsx";



    /**
     * @param fileName 文件名 带扩展名
     * @param firstPart 第一部分最后一列的编号（第一列为 0）
     * @param secondPart 第二部分最后一列的编号
     */
    public static void excel2Json(String fileName,int firstPart,int secondPart){
        XSSFWorkbook workBook = getWorkBook(PATH_BASE + "/" + fileName);

        XSSFSheet sheetAt = workBook.getSheetAt(0);

        List<String> rowName = getRowName(sheetAt);

        int count = sheetAt.getLastRowNum() + 1;
        List<CellRangeAddress> combineCell = getCombineCell(sheetAt);

        System.out.println( fileName + "解析中......");
        for(int i = 1;i < count;i++){
            XSSFRow row = sheetAt.getRow(i);
            Map<String,Object> map = new LinkedHashMap<>();

            for(int j = 0; j < firstPart;j++){
                XSSFCell cell = row.getCell(j);
                String stringCellValue = getValue(cell);
                if(stringCellValue != null){
                    map.put(rowName.get(j),stringCellValue);
                }

            }

            List<Map<String,String>> subList = new LinkedList<>();
            Map<String, Integer> rowNum = getRowNum(combineCell, sheetAt.getRow(i).getCell(0));
            Integer lastRow = rowNum.get("lastRow");
            Integer firstRow = rowNum.get("firstRow");

            if(isMergedRegion(sheetAt,i,0)){

                for(;i <=lastRow;i++){
                    XSSFRow subRow = sheetAt.getRow(i);
                    Map<String,String> sub = new LinkedHashMap<>();
                    for(int m = firstPart;m < secondPart;m++){
                        XSSFCell cell = subRow.getCell(m);
                        sub.put(rowName.get(m),getValue(cell));
                    }
                    subList.add(sub);
                }
                map.put("子类",subList);
                i--;
            }else {
                Map<String,String> sub = new LinkedHashMap<>();
                for(int m = firstPart;m < secondPart;m++){
                    XSSFCell cell = row.getCell(m);
                    sub.put(rowName.get(m),getValue(cell));
                }
                subList.add(sub);
                map.put("子类",subList);
            }
            for(int n = secondPart;n < row.getLastCellNum();n++){
                XSSFCell cell = row.getCell(n);
                String value = getValue(cell);
                map.put(rowName.get(n),value);
            }

            result.add(map);
        }
        System.out.println("解析完成");
        System.out.println("保存中....");

        String s = JSON.toJSONString(result);

        String fileNameWithoutExt = fileName.substring(0, fileName.lastIndexOf("."));
        String fileAbsolutePath = PATH_BASE + "/" + fileNameWithoutExt + ".json";

        try {
            writeToJson(fileAbsolutePath,s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("保存完成:" + fileAbsolutePath);

    }

    /**
     * 获取07以上版本xlsx 工作表
     * @param fileAbsolutePath
     * @return
     */
    public static XSSFWorkbook getWorkBook(String fileAbsolutePath){
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;

        try {
            fis = new FileInputStream(fileAbsolutePath);
            String fileExt = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("."));
            if(EXCEL2007.equals(fileExt)){
                workbook = new XSSFWorkbook(fis);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * 获取标题栏
     * @param sheet
     * @return
     */
    public static List<String> getRowName(XSSFSheet sheet){
        XSSFRow name = sheet.getRow(0);
        List<String> rowName = new LinkedList<>();

        for(int col = 0;col < name.getLastCellNum();col++){
            XSSFCell cell = name.getCell(col);
            String cellValue = cell.getStringCellValue();
            rowName.add(cellValue);
        }
        return rowName;
    }




    /**
     * 解析合并单元格
     * **/
    public static List<CellRangeAddress> getCombineCell(XSSFSheet sheet)
    {
        List<CellRangeAddress> list = new ArrayList<>();
        /**获得一个 sheet 中合并单元格的数量**/
        int sheetMergerCount = sheet.getNumMergedRegions();
        /**遍历所有的合并单元格**/
        for(int i = 0; i<sheetMergerCount;i++)
        {
            /***获得合并单元格保存进list中**/
            CellRangeAddress ca = sheet.getMergedRegion(i);
            list.add(ca);
        }
        return list;
    }

    public static Map<String,Integer> getRowNum(List<CellRangeAddress> combineCell,XSSFCell cell){
        Map<String,Integer> map = new HashMap<>(2);
        for(int i = 1;i < combineCell.size();i++){
            CellRangeAddress cellRangeAddress = combineCell.get(i);
            int firstRow = cellRangeAddress.getFirstRow();
            int lastRow = cellRangeAddress.getLastRow();
            int row = cell.getAddress().getRow();
            if( row >= firstRow && row <= lastRow ){
                map.put("firstRow",firstRow);
                map.put("lastRow",lastRow);
                return map;
            }
        }
        /**
         * 不是合并单元格 则返回当前行
         * **/
        int rowNum = cell.getRow().getRowNum();
        map.put("firstRow",rowNum);
        map.put("lastRow",rowNum);
        return  map;
    }

    /**
     * 判断是否是合并单元格
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static boolean isMergedRegion(XSSFSheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    public static String getValue(XSSFCell cell){
        String value = "";
        if(null==cell){
            return value;
        }
        switch (cell.getCellType()) {
            /**数值型**/
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    /**如果是date类型则 ，获取该cell的date值**/
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);
                }else {// 纯数字
                    BigDecimal big=new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    /**解决1234.0  去掉后面的.0**/
                    if(null != value && !"".equals(value.trim())){
                        String[] item = value.split("[.]");
                        if(1<item.length&&"0".equals(item[1])){
                            value=item[0];
                        }
                    }
                }
                break;
            /** 公式类型**/
            case FORMULA:
                /**读公式计算值**/
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {
                    /***如果获取的数据值为非法值,则转换为获取字符串***/
                    value = cell.getStringCellValue();
                }
                break;
            /*** 布尔类型***/
            case BOOLEAN:
                value = " "+ cell.getBooleanCellValue();
                break;
            default:
                value = cell.getStringCellValue();
        }
        if("null".endsWith(value.trim())){
            value="";
        }
        return value;

    }








}
