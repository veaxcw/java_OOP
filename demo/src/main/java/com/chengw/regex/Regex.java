package com.chengw.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static void main(String[] args) {

        String regex = "[^0-9]";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher("jgvukbjkbk,");

        System.out.println(matcher.matches());
    }

}
