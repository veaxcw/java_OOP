package com.chengw.reflection;

import java.lang.reflect.Constructor;

/**
 * @author chengw
 */
public class Test01  {

	public static void main(String[] args) {
		Class<Book> book = Book.class;
		Constructor[] declaredConstructor = book.getDeclaredConstructors();
		for(int i = 0;i < declaredConstructor.length; i++) {
			Constructor con = declaredConstructor[i];
			System.out.println("参数"+con.isVarArgs());
			Class[] parameterTypes = con.getParameterTypes();
			for(int j = 0; j < parameterTypes.length; j++){
				System.out.println("参数类型" + parameterTypes[j]);
			}
			Class[] exceptionTypes = con.getExceptionTypes();
			for(int j = 0;j < exceptionTypes.length;j++) {
				System.out.println("异常类型" + exceptionTypes[j]);
			}
			Book book1 = null;
			while(book1 == null) {
				try {
					if(i ==1 ) {
						book1 = (Book)con.newInstance("java",10);
					}
					else if(i == 2) {
						book1 = (Book)con.newInstance();
					}
						else {
							Object[] parameters = new Object[] {new String[] {"100","200"}};
							book1 = (Book)con.newInstance(parameters);
						}
				}catch(Exception e) {
					System.out.println("为私有方法");
					con.setAccessible(true);
				}
				book1.print();
			}
		}
		
		

	}

}
