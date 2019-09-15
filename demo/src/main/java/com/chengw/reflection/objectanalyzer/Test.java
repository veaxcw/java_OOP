package com.chengw.reflection.objectanalyzer;

import com.chengw.reflection.demo.Book;

/**
 * @author veax
 */
public class Test {

    public static void main(String[] args) {
        Book book = new Book();
        book.setId(1);
        book.setName("java");
        book.setPrice(10F);

        System.out.println(book.toString());

    }

}
