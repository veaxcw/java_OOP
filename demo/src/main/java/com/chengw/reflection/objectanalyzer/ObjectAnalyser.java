package com.chengw.reflection.objectanalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 对象分析器，实现泛型通用的toString 方法
 *
 * @author veax
 */
public class ObjectAnalyser {

    private List<Object> visited = new ArrayList<>();

    public String toString(Object o) {

        if (null == o) {
            return null;
        }

        if (visited.contains(o)) {
            return "......";
        }

        Class<?> oClass = o.getClass();

        if (oClass == String.class) {
            return (String) o;
        }

        if (oClass.isArray()) {
            StringBuffer r = new StringBuffer(oClass.getComponentType() + "[]{");
            for (int i = 0; i < Array.getLength(o); i++) {
                if (i > 0) {
                    r.append(",");
                }
                Object val = Array.get(o, i);
                /**判断数据是否是基本数据类型**/
                r.append(oClass.getComponentType().isPrimitive() ? val : toString(val));
            }
            return r.append("}").toString();
        }

        StringBuffer r = new StringBuffer(oClass.getName());

        do {
            r.append("[");
            Field[] declaredFields = oClass.getDeclaredFields();
            AccessibleObject.setAccessible(declaredFields, true);
            for (Field f : declaredFields) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    r.append(!r.toString().endsWith("[") ? "," :"");
                    r.append(f.getName() + "=");
                    try{
                        Class t = f.getType();
                        Object val = f.get(o);
                        r.append(t.isPrimitive()?val:toString(val));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            r.append("]");
            oClass = oClass.getSuperclass();
        } while (oClass != Object.class && null != oClass);


        return r.toString();
    }
}
