package com.chengw.stream.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CountLongWords {

    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("f:/word.txt")), StandardCharsets.UTF_8);

        /***
         * '\PL' 匹配任何不在Unicode字母表里面的字符
         * */
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long count = words.stream().filter(w->w.length() > 5).count();


    }
}
