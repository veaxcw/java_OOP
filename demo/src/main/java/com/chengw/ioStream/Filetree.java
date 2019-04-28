package com.chengw.ioStream;

import java.io.File;
import java.io.FileNotFoundException;

public class Filetree {
	Test_allFile file = new Test_allFile();//Ϊ�˵���fox_txt
	@SuppressWarnings("static-access")
	public Filetree(File f) {//ͨ���ݹ�ʵ�ֱ����ļ�Ŀ¼�����е���Ŀ¼���ļ�
		String[] list = f.list();
		String path = f.getAbsolutePath();
		String temp = null;
		try {
			File check = null;
			for(int i = 0; i < list.length;i++){
				 temp = path.concat("/"+list[i]);
				 check = new File(temp);
				 
				 if(check.isDirectory()) {
					 System.out.println(list[i]);
					 System.out.print("\t\t");
					 String txt = list[i] + "\t\t";
					 try {
					 file.fos_txt.writeBytes(txt);;;
					 file.fos_txt.seek(file.fos_txt.length());//�ƶ�ָ�뵽�ļ�ĩβ
					 new Filetree(check);
					 }catch(FileNotFoundException e) {
						 e.printStackTrace();
					 }					 
				 }
				 else if(check.isFile()) {
					 int begin = list[i].lastIndexOf(".");//���ļ�����ǰ�� ������ļ���ʽ
					 String text = "\t" + list[i] + "\t" + list[i].substring(begin+1);
					 System.out.println("\t" + list[i] + "\t" + list[i].substring(begin+1));
					 try {
						 file.fos_txt.writeBytes(text);;
						 file.fos_txt.seek(file.fos_txt.length());
						 }catch(FileNotFoundException e) {
							 e.printStackTrace();
						 }
				 	}
				}
			}catch(Exception e) {
				e.printStackTrace();
		}
		}
}


