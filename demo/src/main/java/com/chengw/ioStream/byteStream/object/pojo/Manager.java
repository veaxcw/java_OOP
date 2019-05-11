package com.chengw.ioStream.byteStream.object.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Manager extends Employee  implements Serializable {

    private static final long serialVersionUID = 3990622185059153558L;

    public Manager() {
    }

    public Manager(String name, String salary) {
        super(name, salary);
    }

    private Employee secretary;
}
