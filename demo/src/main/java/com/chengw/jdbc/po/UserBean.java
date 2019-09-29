package com.chengw.jdbc.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class UserBean implements Serializable {

    private static final long serialVersionUID = -6094389360383384018L;

    private Long id;

    private String name;

    private String code;

    private String sex;

}
