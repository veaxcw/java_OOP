package com.chengw.ioStream;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test_FileStream implements FilenameFilter {
	public static void main(String[] args) {
		String path = "C:/";
		String path_java = "F:/java/java program/OOP/src/reflection/";
		String filename = "Test.txt";
		String filename_java = "Test03.java";
		File f = new File(path);
		String filelist[] = f.list(new Test_FileStream());
		for(int i = 0; i < filelist.length; i++) {
			//����Ŀ¼
			System.out.print(filelist[i]+ "\t\t");
			System.out.println(new File("F:/",filelist[i]).isFile()?"�ļ�":"�ļ���" );
		}
		
		//��ɴ�f3 ��f2 �ĸ���
		File f2 = new File(path,filename);
		File f3 = new File(path_java,filename_java);
		FileInputStream fis = null ;
		FileOutputStream fos = null;
		FileInputStream fis_java = null;
		System.out.println(f2.exists());
		System.out.println(f3.exists());
		try {
			if(f2.exists())
				f2.delete();
			f2.createNewFile();
			System.out.println(f2.getName() + "(" + f2.length() + ")" + "�Ѿ�����");
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			fis = new FileInputStream(f2);
			fos = new FileOutputStream(f2);//��ȡf2�ļ��ľ���·����������д������
			fis_java = new FileInputStream(f3);
			int temp = fis_java.read();//���ļ��ж�ȡһ���ֽ�
			while(temp != -1) {//����˴�f3 ��f2 �ĸ���
				fos.write(temp);
				temp = fis_java.read();
			}
			fis.close();
			fos.close();
			fis_java.close();
			System.out.println(f2.getName() + "(" + f2.length() + ")" + "��ɸ���");
			//Desktop.getDesktop().open(new File("F:/Test.txt"));//���ļ�
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return true;
	}
}
