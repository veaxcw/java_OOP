package com.chengw.reflection.demo;

import java.lang.reflect.Constructor;

/**
 * @author veax
 */
public class ReflectionMethods {

    public static void main(String[] args) {

        Class<Book> book = Book.class;
        Constructor[] declaredConstructor = book.getDeclaredConstructors();
        for (int i = 0; i < declaredConstructor.length; i++) {
            Constructor con = declaredConstructor[i];
            /**return if the method was declared to  take various numbers of args*/
            System.out.println("all " + con.isVarArgs());
            Class[] parameterTypes = con.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                System.out.println("parameter type" + parameterTypes[j]);
            }
            Class[] exceptionTypes = con.getExceptionTypes();
            for (int j = 0; j < exceptionTypes.length; j++) {
                System.out.println("exception type:" + exceptionTypes[j]);
            }
            Book javaSenior = null;
            while (javaSenior == null) {
                try {
                    if (i == 1) {
                        javaSenior = (Book) con.newInstance("java senior", 10);
                    } else if (i == 2) {
                        javaSenior = (Book) con.newInstance();
                    } else {
                        Object[] parameters = new Object[]{new String[]{"100", "200"}};
                        javaSenior = (Book) con.newInstance(parameters);
                    }
                } catch (Exception e) {
                    System.out.println("the constructor is not accessible");
                    con.setAccessible(true);
                }
                javaSenior.print();
            }
        }


    }

}
