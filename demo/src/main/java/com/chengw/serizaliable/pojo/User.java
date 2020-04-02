package com.chengw.serizaliable.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chengwei
 */
@Data
public class User implements Serializable {

    public User(String name,Integer age) {
        this.age = age;
        this.name = name;
    }

    private static final long serialVersionUID = -267184169920622138L;

    private String name;

    private Integer age;

}
