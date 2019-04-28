package com.chengw.exception;

public class SuperCatch {

	public static void main(String[] args) {
		try {
			System.out.println(calulate(2,0));
		}catch(ArithmeticException e) {//������������ĳ���֮ǰ������ͻᱨ��
			System.out.println("�����쳣");
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("���౻ִ����");
		}finally {
			System.out.println("....");
		}

	}

	private static int calulate(int i, int j) {
		// TODO Auto-generated method stub
		return i/j;
	}

}
