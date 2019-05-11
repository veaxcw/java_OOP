package com.chengw.ioStream.nio.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class NIOFileOperation {

    public NIOFileOperation() {
    }

    /**文件读写**/
    public static void fileReadAndWrite(){
        Path path = Paths.get("f:","employee.dat");
        try {
            byte[] bytes = Files.readAllBytes(path);
            for(byte b:bytes){
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**遍历**/
    public static void travel(){
        Path path = Paths.get("f:");
        try (Stream<Path> entries = Files.walk(path)) {
            Iterator<Path> i =  entries.iterator();
            while(i.hasNext()){
                Path p = i.next();
                System.out.println(p.getFileName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
       // fileReadAndWrite();
        travel();
    }

}
