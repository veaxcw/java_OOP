package com.chengw.reflection;

import java.lang.reflect.Field;

public class Test03  {

	public static void main(String[] args) {
		Book2 book = new Book2();
		Class<? extends Book2> class1 = book.getClass();
		 Field[] declaredField = class1.getDeclaredFields();
		 for(int i = 0; i < declaredField.length;i++) {
			 Field field = declaredField[i];//�������б�����Ա 
			 System.out.println("��Ա����Ϊ��" + field.getName());
			 Class<?> fieldType = field.getType();//��ȡ��Ա����  ������һ����
			 System.out.println("��Ա���ͣ�" + fieldType);
			 boolean isturn = true;
			 while(isturn) {
				 try {
					 //�����Ա����Ϊprivate ���׳��쳣
					 isturn = false;
					 System.out.println("�޸�ǰ��Ա��ֵ��" + field.get(book));
					 if(fieldType.equals(int.class)) {
						 System.out.println("����setInt�޸ĳ�Ա������ֵ");
						 field.setInt(book, 12);
					 }
					 else if(fieldType.equals(float.class)) {
						 System.out.println("����setFloat�޸ĳ�Ա������ֵ");
						 field.setFloat(book, 29.815f);//�������ڱ����ʱ��ɨ�赽��һ��29.815���ڲ���f������£������ж�����double���ͣ�
						 							   //Ӧ�ô���һ��8���ֽڵĿռ�����ţ��Ӷ����ܻ������ʧ���ȵ����⣻����f��
						 							//�������ͻᴴ��һ��4�ֽڵĿռ�����š�float����������������ʱ��Ĭ��ת��������Ҫȷ�����ͣ�����f��
					 }
					 	else if(fieldType.equals(boolean.class)) {
					 		System.out.println("����setBoolean�����޸ĳ�Ա����ֵ");
					 		field.setBoolean(book, true);
					 	}
					 		else {
					 			System.out.println("����set�����޸ĳ�Ա������ֵ");
					 			field.set(book, "java ���");
					 		}
					 System.out.println("�޸ĺ�ĳ�Ա����ֵ��" + field.get(book));
				 }catch(Exception e){
					 System.out.println("�޸ĳ�Ա����ֵ�����쳣");
					 e.printStackTrace();
					 field.setAccessible(true);
					 isturn = true;
				 }
			 }
			 
		 }

	}

}
