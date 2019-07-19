package com.chengw.utils.uploadUtil;

import lombok.Data;

/**
 * @author chengw
 */
@Data
public class CommonResponse {

    private int code;
    private String message;
    private Object result;

}
