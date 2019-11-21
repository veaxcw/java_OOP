package com.chengw.designpattern.factory;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.ResolvableType;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * BeanFactory实现基本原理:
 *
 *        1.使用简单工厂模式来处理bean容器。
 *
 *         2.解析xml文件，获取配置中的元素信息。
 *
 *         3.利用反射获实例化配置信息中的对象。
 *
 *         4.如果有对象注入，使用invoke()方法。
 *
 *         5.实例化的对象放入bean容器中，并提供getBean方法。
 *
 * @author chengwei**/
public class BeanFactory implements org.springframework.beans.factory.BeanFactory {


    private Map<String,Object> container = new HashMap<>();
    private Object object;

    public BeanFactory() {
    }

    public BeanFactory(String xml) {
        init(xml);
    }


    /**初始化容器**/
    private void init(String xml){

        /**
         * SAX:Stream API for XML
         * DOM:document object model
         * 简单点说：
         * 这玩意是用来解析xml文件的
         * */
        try {
            SAXReader reader = new SAXReader();

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            /**
             * 获取xml文件
             * **/
            InputStream ins = classLoader.getResourceAsStream(xml);

            Document doc = reader.read(ins);
            Element root = doc.getRootElement();
            Element foo;


            Method method;

            //遍历bean
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()){
                foo = iterator.next();
                Attribute id = foo.attribute("id");
                Attribute cls = foo.attribute("class");

                //利用反射机制实例化bean
                Class<?> bean = Class.forName(cls.getText());

                //获取对应的class信息
                BeanInfo beanInfo = Introspector.getBeanInfo(bean);
                PropertyDescriptor pd[] = beanInfo.getPropertyDescriptors();

                //todo 获取scope
                String scope = foo.attribute("scope").getValue();
                //根据scope类型创建实例
                if(scope.equalsIgnoreCase("singleton")&& container.containsKey(id.getText())){
                    object = container.get(id.getText());
                    break;
                }else if(scope.equalsIgnoreCase("prototype")|| !container.containsKey(id.getText())){
                    object = bean.newInstance();
                }
                //遍历bean的Properties
                Iterator<Element> it = foo.elementIterator("property");
                while(it.hasNext()){
                    Element foo2 = it.next();
                    Attribute name = foo2.attribute("name");
                    String value = foo2.attributeValue("value");

                    //利用反射调用set方法注入value
                    for(int k = 0; k < pd.length; k++){
                        if(pd[k].getName().equalsIgnoreCase(name.getText())){
                            //获取set方法
                            method = pd[k].getWriteMethod();
                            //调用set方法
                            method.invoke(object,value);
                        }
                    }


                }
                /**将bean注入容器**/
                container.put(id.getText(),object);
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    public Object getBean(String bean){
        return  container.get(bean);
    }

    @Override
    public <T> T getBean(String s, Class<T> aClass) throws BeansException {
        return null;
    }

    @Override
    public Object getBean(String s, Object... objects) throws BeansException {
        return container.get(s);
    }

    @Override
    public <T> T getBean(Class<T> aClass) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> aClass, Object... objects) throws BeansException {
        return null;
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(ResolvableType resolvableType) {
        return null;
    }

    @Override
    public boolean containsBean(String s) {
        return false;
    }

    @Override
    public boolean isSingleton(String s) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isPrototype(String s) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isTypeMatch(String s, ResolvableType resolvableType) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isTypeMatch(String s, Class<?> aClass) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public Class<?> getType(String s) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public String[] getAliases(String s) {
        return new String[0];
    }
}
