package com.chengw.jvm.byteCode.demo;


/**
 * 阅读字节码写的demo
 * 熟悉反汇编工具用的demo
 * **/
public class demo {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = a + b;

        System.out.println(c);
    }
}
/**
 * 反汇编得到的字节码
Classfile /F:/chengw/java/java/demo/target/classes/com/chengw/jvm/byteCode/demo/demo.class
Last modified 2019-5-6; size 604 bytes
        MD5 checksum 16e289f2c2b1379b0a61fefe7585b19f
        Compiled from "demo.java"
public class com.chengw.jvm.byteCode.demo.demo
        minor version: 0
        major version: 52
        flags: ACC_PUBLIC, ACC_SUPER
        Constant pool:
        #1 = Methodref          #5.#23         // java/lang/Object."<init>":()V
        #2 = Fieldref           #24.#25        // java/lang/System.out:Ljava/io/PrintStream;
        #3 = Methodref          #26.#27        // java/io/PrintStream.println:(I)V
        #4 = Class              #28            // com/chengw/jvm/byteCode/demo/demo
        #5 = Class              #29            // java/lang/Object
        #6 = Utf8               <init>
   #7 = Utf8               ()V
           #8 = Utf8               Code
           #9 = Utf8               LineNumberTable
           #10 = Utf8               LocalVariableTable
           #11 = Utf8               this
           #12 = Utf8               Lcom/chengw/jvm/byteCode/demo/demo;
           #13 = Utf8               main
           #14 = Utf8               ([Ljava/lang/String;)V
           #15 = Utf8               args
           #16 = Utf8               [Ljava/lang/String;
           #17 = Utf8               a
           #18 = Utf8               I
           #19 = Utf8               b
           #20 = Utf8               c
           #21 = Utf8               SourceFile
           #22 = Utf8               demo.java
           #23 = NameAndType        #6:#7          // "<init>":()V
           #24 = Class              #30            // java/lang/System
           #25 = NameAndType        #31:#32        // out:Ljava/io/PrintStream;
           #26 = Class              #33            // java/io/PrintStream
           #27 = NameAndType        #34:#35        // println:(I)V
           #28 = Utf8               com/chengw/jvm/byteCode/demo/demo
           #29 = Utf8               java/lang/Object
           #30 = Utf8               java/lang/System
           #31 = Utf8               out
           #32 = Utf8               Ljava/io/PrintStream;
           #33 = Utf8               java/io/PrintStream
           #34 = Utf8               println
           #35 = Utf8               (I)V
           {
public com.chengw.jvm.byteCode.demo.demo();
        descriptor: ()V
        flags: ACC_PUBLIC
        Code:
        stack=1, locals=1, args_size=1
        0: aload_0
        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
        4: return
        LineNumberTable:
        line 8: 0
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        0       5     0  this   Lcom/chengw/jvm/byteCode/demo/demo;

public static void main(java.lang.String[]);
        descriptor: ([Ljava/lang/String;)V
        flags: ACC_PUBLIC, ACC_STATIC
        Code:
        stack=2, locals=4, args_size=1
        0: iconst_1
        1: istore_1
        2: iconst_2
        3: istore_2
        4: iload_1
        5: iload_2
        6: iadd
        7: istore_3
        8: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        11: iload_3
        12: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
        15: return
        LineNumberTable:
        line 11: 0
        line 12: 2
        line 13: 4
        line 15: 8
        line 16: 15
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        0      16     0  args   [Ljava/lang/String;
        2      14     1     a   I
        4      12     2     b   I
        8       8     3     c   I
        }
        SourceFile: "demo.java"
**/