package com.chengw.nio.buffer;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author chengw
 */
public class bufferDemoTest {

    @Test
    public void copy() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("F:/VolatileViableDemo.class");
        FileChannel channel = fis.getChannel();

        Path path = Paths.get("F:/copy.class");
        File file = path.toFile();

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileChannel fos = new RandomAccessFile(file,"rw").getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            while(channel.read(buffer) != -1){
                buffer.flip();
                fos.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
