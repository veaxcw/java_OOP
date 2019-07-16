package com.chengw.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class Excel2JSON {

    private static final String PATH_BASE = "E:/材料做法表 文档/00-内容库 v1.0";

    private static  List<Map<String,Object>> result = new LinkedList<>();

    private static  List<String> rowName = new LinkedList<>();



    public static void main(String[] args) throws FileNotFoundException {
        String filePath = PATH_BASE  + "/材料做法数据表格-地面.xlsx";
        FileInputStream fis  = new FileInputStream(filePath);
        Boolean combineFlag = true;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheetAt = workbook.getSheetAt(0);
            XSSFRow name = sheetAt.getRow(0);

            for(int col = 0;col < name.getLastCellNum();col++){
                XSSFCell cell = name.getCell(col);
                String cellValue = cell.getStringCellValue();
                rowName.add(cellValue);
            }

            int count = sheetAt.getLastRowNum() + 1;

            List<CellRangeAddress> combineCell = getCombineCell(sheetAt);

            for(int i = 1;i < count;i++){
                XSSFRow row = sheetAt.getRow(i);
                Map<String,Object> map = new LinkedHashMap<>();

                for(int j = 0; j < 9;j++){
                    XSSFCell cell = row.getCell(j);
                    String stringCellValue = getValue(cell);
                    if(stringCellValue != null && !stringCellValue.equals("")){
                        map.put(rowName.get(j),stringCellValue);
                    }

                }
                for(int n = 14;n < row.getLastCellNum();n++){
                    XSSFCell cell = row.getCell(n);
                    String value = getValue(cell);
                    map.put(rowName.get(n),value);
                }


                List<Map<String,String>> subList = new LinkedList<>();

                if(isMergedRegion(sheetAt,i,0)){
                    Map<String, Integer> rowNum = getRowNum(combineCell, sheetAt.getRow(i).getCell(0));
                    Integer lastRow = rowNum.get("lastRow");
                    Integer firstRow = rowNum.get("firstRow");
                    for(;i <=lastRow;i++){
                        XSSFRow subRow = sheetAt.getRow(i);
                        Map<String,String> sub = new LinkedHashMap<>();
                        for(int m = 9;m < 14;m++){
                            XSSFCell cell = subRow.getCell(m);
                            sub.put(rowName.get(m),getValue(cell));
                        }
                        subList.add(sub);
                    }
                    map.put("子类",subList);

                }
                i--;

                result.add(map);
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = JSON.toJSONString(result);
        Object parse = JSONArray.parse(s);

        try {
            writeToJson(PATH_BASE + "/测试.txt",s);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static void writeToJson(String filePath,String string) throws IOException
    {
        File file = new File(filePath);
        if(!file.exists()) {
            file.createNewFile();
        }
        char [] stack = new char[1024];
        int top=-1;
        StringBuffer sb = new StringBuffer();
        char [] charArray = string.toCharArray();
        for(int i=0;i<charArray.length;i++){
            char c= charArray[i];
            if ('{' == c || '[' == c) {
                stack[++top] = c;
                sb.append(charArray[i]);
                for (int j = 0; j <= top; j++) {
                    sb.append("\r\n");
                }
                continue;
            }
            if ((i + 1) <= (charArray.length - 1)) {
                char d = charArray[i+1];
                if ('}' == d || ']' == d) {
                    top--;
                    sb.append(charArray[i]);
                    for (int j = 0; j <= top; j++) {
                        sb.append("\r\n");
                    }
                    continue;
                }
            }
            if (',' == c) {
                sb.append(charArray[i] + "\r\n");
                for (int j = 0; j <= top; j++) {
                    sb.append("");
                }
                continue;
            }
            sb.append(c);
        }
        Writer write = new FileWriter(file);
        write.write(sb.toString());
        write.flush();
        write.close();
    }

    /**
     * 解析合并单元格
     * **/
    public static List<CellRangeAddress> getCombineCell(XSSFSheet sheet)
    {
        List<CellRangeAddress> list = new ArrayList<CellRangeAddress>();
        //获得一个 sheet 中合并单元格的数量
        int sheetmergerCount = sheet.getNumMergedRegions();
        //遍历所有的合并单元格
        for(int i = 0; i<sheetmergerCount;i++)
        {
            //获得合并单元格保存进list中
            CellRangeAddress ca = sheet.getMergedRegion(i);
            list.add(ca);
        }
        return list;
    }

    public static Map<String,Integer> getRowNum(List<CellRangeAddress> combineCell,XSSFCell cell){
        Map<String,Integer> map = new HashMap<>();
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
        return  null;
    }

    private static boolean isMergedRegion(XSSFSheet sheet,int row ,int column) {
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

    public static String getValue(XSSFCell cell){
        String value = "";
        if(null==cell){
            return value;
        }
        switch (cell.getCellType()) {
            //数值型
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);;
                }else {// 纯数字
                    BigDecimal big=new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    //解决1234.0  去掉后面的.0
                    if(null != value && !"".equals(value.trim())){
                        String[] item = value.split("[.]");
                        if(1<item.length&&"0".equals(item[1])){
                            value=item[0];
                        }
                    }
                }
                break;
            //字符串类型
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue().toString();
                break;
            // 公式类型
            case Cell.CELL_TYPE_FORMULA:
                //读公式计算值
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {
                    // 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getStringCellValue().toString();
                }
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                value = " "+ cell.getBooleanCellValue();
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        if("null".endsWith(value.trim())){
            value="";
        }
        return value;

    }






}
