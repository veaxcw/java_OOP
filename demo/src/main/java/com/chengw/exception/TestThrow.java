package com.chengw.exception;

public class TestThrow {

	public static void main(String[] args) {
		try {
			test();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
	static void test() throws StringIndexOutOfBoundsException{
		String str = "java";
		for(int i = 0; i < 6; i++)
			System.out.println(str.substring(i,i+1));
	}

}
