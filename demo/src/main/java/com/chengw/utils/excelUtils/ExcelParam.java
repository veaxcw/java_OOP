package com.chengw.utils.excelUtils;

import lombok.Data;

/**
 * @author chengw
 */
@Data
public class ExcelParam {

    private Integer sheet = 0;

    /**
     * 行从0开始编号
     * 默认从第二行开始读取数据
     * **/
    private Integer startRow = 1;

    private Integer endRow;

    /**
     * 默认从第一列开始读取数据
     * ***/
    private Integer startColumn = 0;

    private Integer endColumn;



}
