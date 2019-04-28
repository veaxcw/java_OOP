package com.chengw.ioStream;
import java.io.IOException;
import java.lang.System;
public class Test_SystemStream {
	public static void main(String[] args) {
		byte[] byteData = new byte[20];
		System.out.println("please Input letters");
		try {
			System.in.read(byteData);
		}catch(IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < byteData.length; i++)
			System.out.print((char)byteData[i]);
	}
}
