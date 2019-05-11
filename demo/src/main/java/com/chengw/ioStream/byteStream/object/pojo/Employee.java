package com.chengw.ioStream.byteStream.object.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1176792671832720660L;

    public Employee() {
    }

    public Employee(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    private String name;

    private String salary;




}
