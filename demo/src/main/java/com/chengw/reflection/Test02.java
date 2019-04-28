package com.chengw.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Test02 {
//���ʷ���
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Book1 book = new Book1();
		Class<? extends Book1> class1 = book.getClass();//��ȡbook���з���
		java.lang.reflect.Method[] declaredMethods = class1.getDeclaredMethods();
		for(int i = 0; i < declaredMethods.length; i++) {
			Method method = declaredMethods[i];
			System.out.println("��������" + method.getName());
			System.out.println("�����Ƿ���пɱ����" + method.isVarArgs());
			System.out.println("������������һ��ʱ��");
			Class[] methodType = method.getParameterTypes();
			for(int j = 0; j < methodType.length; j++) {
				System.out.println("  "+ methodType[j]);
			}
			System.out.println("����ֵ���ͣ�" + method.getReturnType());
			System.out.println("�쳣���Ϳ����У�");
			Class[] exceptionType = method.getExceptionTypes();
			for(int j = 0; j < exceptionType.length; j++){
				System.out.println("  " + exceptionType[j]);
			}
			boolean isTurn = true;
			while(isTurn) {
				try {//�����Ա�����ķ���Ȩ��ʱprivate���׳��쳣
					isTurn = false;
					if(method.getName().equals("staticMethod")) {
						//����û�в����ķ���
						method.invoke(book);
					}
					else if(method.getName().equals("publicMethod")) {
						//����һ�������ķ���
						System.out.println("publicMethod(10)�ķŻ�ֵΪ" + method.invoke(book, 10));
					}
						else if(method.getName().equals("protectedMethod")) {
							//�������������ķ���
							System.out.println("protectedMethod(10,5)�ķ���ֵ��" + method.invoke(book, "10",5));
						}
						 	else if(method.getName().equals("privateMethod")){
						 		//���ÿɱ�����ķ���
						 		Object[] parameters = new Object[] {new String[] {"J","A","V","A"}};
						 		System.out.println("privateMethod����ֵ�ǣ�" + method.invoke(book,parameters));
						 	}
				}catch(Exception e) {
					System.out.println("���ó�Ա����ʱ�׳��쳣��ִ��SetAccessible()����");
					method.setAccessible(true);
					isTurn = true;
					
				}
			}
			
		}
		

	}

}
