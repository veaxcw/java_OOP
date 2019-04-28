package com.chengw.reflection;

public class Book1 {
	static void staticMethod() {
		System.out.println("ִ�о�̬����");
	}
	public int publicMethod(int i) {
		System.out.println("ִ��public����");
		return i++;
	}
	protected int protectedMethod(String s,int i) throws NumberFormatException{
		System.out.println("ִ��protected����");
		return Integer.valueOf(s) + i;
	}
	private String privateMethod(String...strings) {
		System.out.println("ִ��private����");
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < strings.length; i++) {
			str.append(strings[i]);
		}
		return str.toString();
	}
}
