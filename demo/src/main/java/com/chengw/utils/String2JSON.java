package com.chengw.utils;

import java.io.*;

public class String2JSON {

    /**
     * 保存为带格式的json
     * @param filePath 文件全路径
     * @param string json string
     * @throws IOException
     */
    public static void writeToJson(String filePath,String string) throws IOException
    {
        File file = new File(filePath);
        if(!file.exists()) {
            file.createNewFile();
        }
        char [] stack = new char[1024];
        int top=-1;
        StringBuffer sb = new StringBuffer();
        char [] charArray = string.toCharArray();
        for(int i=0;i<charArray.length;i++){
            char c= charArray[i];
            if ('{' == c || '[' == c) {
                stack[++top] = c;
                sb.append(charArray[i]);
                sb.append("\n");
                continue;
            }
            if ((i + 1) <= (charArray.length - 1)) {
                char d = charArray[i+1];
                if ('}' == d || ']' == d) {
                    top--;
                    sb.append(charArray[i]);
                    sb.append("\n");
                    continue;
                }
            }
            boolean flag = (i >= 1)&&('"' == charArray[i-1] || '}' == charArray[i-1] || ']' == charArray[i-1]) &&',' == c;
            if (flag) {
                sb.append(charArray[i] + "\n");
                continue;
            }
            sb.append(c);
        }
        Writer write = new FileWriter(file);
        write.write(sb.toString());
        write.flush();
        write.close();
    }


    /**
     * 保存不带格式的json
     * @param filePath 目标文件全路径
     * @param s json字符串
     */
    public static void save(String filePath,String s) {
        File file = null;
        FileOutputStream fis = null;
        try {
            file = new File(filePath);
            if(!file.exists()) {
                file.createNewFile();
            }
            fis = new FileOutputStream(file);
            fis.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
