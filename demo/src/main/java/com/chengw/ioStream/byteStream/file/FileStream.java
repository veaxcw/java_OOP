package com.chengw.ioStream.byteStream.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileStream {



    public static void main(String[] args) {

        FileTree traversing = null;
        try {
            traversing = new FileTree();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String root = "f:/";

        traversing.getFileTree(new File(root));

    }

}
