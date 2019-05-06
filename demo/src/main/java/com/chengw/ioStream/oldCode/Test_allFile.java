package com.chengw.ioStream.oldCode;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Test_allFile {
	public static RandomAccessFile fos_txt = null;
	public static void main(String[] args) {
		String path = "F:/";
		File f = new File(path);
		File f2 = new File("F:/","Test.txt");
		try {
			fos_txt = new RandomAccessFile(f2,"rw");
			new Filetree(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				fos_txt.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
