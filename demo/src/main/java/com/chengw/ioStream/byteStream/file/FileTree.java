package com.chengw.ioStream.byteStream.file;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class FileTree {

	private RandomAccessFile logWrite;

	public FileTree() throws FileNotFoundException {

		File log = new File("f:/log.txt");

		if(!log.exists()){
			try {
				log.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}


		logWrite = new RandomAccessFile(log,"rw");

	}
	/**递归实现遍历根目录下所有文件和文件夹**/
	public int getFileTree(File root){


		String[] list = root.list();
		String path = root.getAbsolutePath();
		String childPath ;
		try {
			File leaf ;
			for(int i = 0; i < list.length;i++){
				childPath = path.concat("/"+list[i]);
				leaf = new File(childPath);
				if(leaf.isDirectory()) {
					System.out.println(list[i]);
					System.out.print("\t\t");
					String txt = list[i] + "\t\t";
					logWrite.writeBytes(txt);
					//定位到文件末尾
					logWrite.seek(logWrite.length());
					//递归调用
					getFileTree(leaf);
				}
				else if(leaf.isFile()) {
					int begin = list[i].lastIndexOf(".");
					String txt = "\t" + list[i] + "\t" + list[i].substring(begin+1);
					System.out.println("\t" + list[i] + "\t" + list[i].substring(begin+1));
					logWrite.writeBytes(txt);
					//定位到文件末尾
					logWrite.seek(logWrite.length());
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		return 0;
		}
}


