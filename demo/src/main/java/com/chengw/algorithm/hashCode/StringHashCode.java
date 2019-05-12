package com.chengw.algorithm.hashCode;

public class StringHashCode {


    public int stringHashCode(String s){
        char[] value = new char[s.length()];

        int hash = 0;

        for(int i = 0; i < s.length();i++)
            value[i] = s.charAt(i);
        for(int i = 0;i < s.length();i++)
            hash = hash * 31 + value[i];

        return hash;


    }

}
