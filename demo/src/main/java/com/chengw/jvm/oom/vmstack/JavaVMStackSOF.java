package com.chengw.jvm.oom.vmstack;

/***
 * VM 参数：-Xss228k
 * 规定了每个线程VMStack的大小
 * */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();

        try{
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("Stack Length:" + oom.stackLength);
            throw  e;
        }
    }

}
