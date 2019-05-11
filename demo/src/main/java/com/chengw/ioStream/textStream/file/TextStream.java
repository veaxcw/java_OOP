package com.chengw.ioStream.textStream.file;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
public class TextStream {

	public static void main(String[] args) {
		FileReader fr = null;
		File fr1 = null;
		FileWriter fw = null;
		Scanner sc = new Scanner(System.in);
		try {
			fr = new FileReader("F:/employee.dat");
			int temp;
			while((temp = fr.read()) != -1) {
				System.out.println(Integer.toHexString(temp));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		fr1 = new File("F:/","text.txt");
		try {
			if(fr1.exists()) {
				fr1.delete();
			}
			fr1.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			fw = new FileWriter("F:/text.txt");
			for(int i =0 ;i < 4; i++) {
				String name = sc.next();
				fw.write(name + "\r\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
				try {
					fw.close();;
				}catch(IOException e) {
					e.printStackTrace();
				}
		}
		

	}

}
