package com.chengw.utils.excelUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chengw.utils.jsonUtils.String2JSONFileUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Excel 转Json
 *
 * @author chengw
 */
public class ExcelUtils {


    public static final String EXCEL2003 = ".xls";

    public static final String EXCEL2007 = ".xlsx";

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 普通Excel 表格解析
     *doc 文档库
     * @param fileAbsolutePath
     * @param params
     * @return
     */
    public static JSONArray excel2Json(String fileAbsolutePath, ExcelParam params) {

        List<Map<String, Object>> result = new LinkedList<>();

        int startClomun = params.getStartColumn();
        int endColumn = params.getEndColumn();

        Workbook workBook = getWorkBook(fileAbsolutePath);
        Sheet sheetAt = workBook.getSheetAt(params.getSheet());

        List<String> rowName = getRowName(sheetAt);
        int count = sheetAt.getLastRowNum() + 1;

        /**默认从第二行开始解析，不同表格手动**/
        logger.info("开始解析");
        for (int i = params.getStartRow(); i < count; i++) {
            Row row = sheetAt.getRow(i);
            Map<String, Object> map = new LinkedHashMap<>();

            for (int col = startClomun; col < endColumn; col++) {
                Cell cell = row.getCell(col);
                String value = getValue(cell);
//                map.put(DocNameUtils.NAME.get(rowName.get(col)), value);
            }
            result.add(map);

        }
        String s = JSON.toJSONString(result);
        JSONArray objects = JSONArray.parseArray(s);

        return objects;
    }

    /**
     * 包含合并单元格Excel的解析
     *材料做法内容库
     * @param fileAbsolutePath 文件名 带扩展名
     * @param firstPart        第一部分最后一列的编号（第一列为 0）
     * @param secondPart       第二部分最后一列的编号
     */
    public static JSONArray excel2Json(String fileAbsolutePath, int firstPart, int secondPart) {

        List<Map<String, Object>> result = new LinkedList<>();

        Workbook workBook = getWorkBook(fileAbsolutePath);

        Sheet sheetAt = workBook.getSheetAt(0);

        List<String> rowName = getRowName(sheetAt);

//        int count = sheetAt.getLastRowNum();
        int count = sheetAt.getPhysicalNumberOfRows();
        List<CellRangeAddress> combineCell = getCombineCell(sheetAt);

        System.out.println(fileAbsolutePath + "解析中......");
        for (int i = 1; i < count; i++) {
            Row row = sheetAt.getRow(i);
            Map<String, Object> map = new LinkedHashMap<>();

            for (int j = 0; j < firstPart; j++) {
                Cell cell = row.getCell(j);
                String stringCellValue = getValue(cell);
                if (stringCellValue != null) {
//                    map.put(MaterialNameUtils.NAME.get(rowName.get(j)), stringCellValue);
                }

            }

            List<Map<String, String>> subList = new LinkedList<>();
            logger.info("正在解析第{}行",i + 1);
            Map<String, Integer> rowNum = getRowNum(combineCell, sheetAt.getRow(i).getCell(0));
            Integer lastRow = rowNum.get("lastRow");
            Integer firstRow = rowNum.get("firstRow");

            int c = isMergedRegion(sheetAt, i, 0) ? lastRow : i;

            for (; i <= c; i++) {
                Row subRow = sheetAt.getRow(i);
                Map<String, String> sub = new LinkedHashMap<>();
                for (int m = firstPart; m < secondPart; m++) {
                    Cell cell = subRow.getCell(m);
//                    sub.put(MaterialNameUtils.NAME.get(rowName.get(m)), getValue(cell));
                }
                subList.add(sub);
            }
            map.put("instructionLevels", subList);
            i--;
            for (int n = secondPart; n < rowName.size(); n++) {
                Cell cell = row.getCell(n);
                String value = getValue(cell);
//                map.put(MaterialNameUtils.NAME.get(rowName.get(n)), value);
            }

            result.add(map);
        }
        System.out.println("解析完成");
        System.out.println("保存中....");

        String s = JSON.toJSONString(result);

        JSONArray objects = JSONArray.parseArray(s);

        String fileNameWithoutExt = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("/") + 1, fileAbsolutePath.lastIndexOf("."));
        String jsonFileAbsolutePath = fileAbsolutePath.substring(0, fileAbsolutePath.lastIndexOf("/")) + "/json/" + fileNameWithoutExt + ".json";

        try {
            String2JSONFileUtil.writeToJson(jsonFileAbsolutePath, s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("保存完成:" + jsonFileAbsolutePath);

        return objects;

    }

    /**
     * 获取03 或者074以上版本xlsx 工作表
     *
     * @param filePath
     * @return
     */
    public static Workbook getWorkBook(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (EXCEL2003.equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (EXCEL2007.equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 获取标题栏
     *
     * @param sheet
     * @return
     */
    public static List<String> getRowName(Sheet sheet) {
        Row name = sheet.getRow(0);
        List<String> rowName = new LinkedList<>();

        for (int col = 0; col < name.getLastCellNum(); col++) {
            Cell cell = name.getCell(col);
            String cellValue = cell.getStringCellValue();
            rowName.add(cellValue);
        }
        return rowName;
    }

    /**
     * 解析合并行单元格
     *
     * @param sheet
     **/
    public static List<CellRangeAddress> getCombineCell(Sheet sheet) {
        List<CellRangeAddress> list = new ArrayList<>();
        /**获得一个 sheet 中合并单元格的数量**/
        int sheetMergerCount = sheet.getNumMergedRegions();
        /**遍历所有的合并单元格**/
        for (int i = 0; i < sheetMergerCount; i++) {
            /***只保存行合并的单元格**/
            CellRangeAddress ca = sheet.getMergedRegion(i);
            if (ca.getFirstColumn() == 0 && ca.getLastColumn() == 0) {
                list.add(ca);
            }
        }
        return list;
    }

    /**
     * 返回合并单元格的起始行，于束行
     *
     * @param combineCell
     * @param cell
     * @return
     */
    public static Map<String, Integer> getRowNum(List<CellRangeAddress> combineCell, Cell cell) {
        Map<String, Integer> map = new HashMap<>(2);
        int row = cell.getAddress().getRow();
        for (int i = 0; i < combineCell.size(); i++) {
            CellRangeAddress cellRangeAddress = combineCell.get(i);
            int firstRow = cellRangeAddress.getFirstRow();
            int lastRow = cellRangeAddress.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                map.put("firstRow", firstRow);
                map.put("lastRow", lastRow);
                return map;
            }
        }
        /**
         * 不是合并单元格 则返回当前行
         * **/
        int rowNum = cell.getRow().getRowNum();
        map.put("firstRow", rowNum);
        map.put("lastRow", rowNum);
        return map;
    }

    /**
     * 判断是否是合并单元格
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    public static String getValue(Cell cell) {
        String value = "";
        if (null == cell) {
            return value;
        }
        cell.setCellType(CellType.STRING);
        switch (cell.getCellType()) {
            /**数值型**/
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    /**如果是date类型则 ，获取该cell的date值**/
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);
                } else {// 纯数字
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    /**解决1234.0  去掉后面的.0**/
                    if (null != value && !"".equals(value.trim())) {
                        String[] item = value.split("[.]");
                        if (1 < item.length && "0".equals(item[1])) {
                            value = item[0];
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
                if (null != value && !"".equals(value.trim())) {
                    String[] item = value.split("[.]");
                    if (item.length > 1 && "0".equals(item[1])) {
                        value = item[0];
                    }
                }
                break;
            /*** 布尔类型***/
            case BOOLEAN:
                value = " " + cell.getBooleanCellValue();
                break;
            default:
                value = cell.getStringCellValue().replaceAll("\n","");
        }
        if ("null".endsWith(value.trim())) {
            value = "";
        }
        return value;

    }


}
