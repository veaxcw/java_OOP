package com.chengw.reflection;

import java.lang.reflect.Constructor;

public class Test01  {
//���ʹ��췽��
	public static void main(String[] args) {
		Class<Book> book = Book.class;//��ȡ��̬��Book
		@SuppressWarnings("rawtypes")
		Constructor[] declaredConstructor = book.getDeclaredConstructors();//����һ��Consturctor�����飬����ȡbook���еĹ��췽��
		for(int i = 0;i < declaredConstructor.length; i++) {
			@SuppressWarnings("rawtypes")
			Constructor con = declaredConstructor[i];
			System.out.println("�Ƿ�������ɱ������Ĳ�����"+con.isVarArgs());
			@SuppressWarnings("rawtypes")
			Class[] parameterTypes = con.getParameterTypes();//��ȡ���в�������
			for(int j = 0; j < parameterTypes.length; j++){
				System.out.println("�������ͣ�" + parameterTypes[j]);
			}
			@SuppressWarnings("rawtypes")
			Class[] exceptionTypes = con.getExceptionTypes();
			for(int j = 0;j < exceptionTypes.length;j++) {
				System.out.println("�쳣���ͣ�" + exceptionTypes[j]);
			}
			//����һ��δʵ��������book1
			Book book1 = null;
			while(book1 == null) {
				try {
					if(i ==1 ) {
						book1 = (Book)con.newInstance("java�̳�",10);
					}
					else if(i == 2) {
						book1 = (Book)con.newInstance();
					}
						else {
							Object[] parameters = new Object[] {new String[] {"100","200"}};
							book1 = (Book)con.newInstance(parameters);
						}
				}catch(Exception e) {
					System.out.println("��������ʱ�����쳣������ִ��setAccessible()");
					con.setAccessible(true);
				}
				book1.print();
			}
		}
		
		

	}

}
