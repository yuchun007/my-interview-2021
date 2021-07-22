package com.yc.interview.java;

public class TransferDemo {

    public static void test1(String str){
        System.out.println("test1 value1 " + System.identityHashCode(str));
        str = "xxx";
        System.out.println("test1 value1 " + System.identityHashCode(str));

    }

    public static void main(String[] args) {
        String str = "abc";
        System.out.println("main value1 " + System.identityHashCode(str));
        test1(str);
        System.out.println("main value1 " + System.identityHashCode(str));
    }
}
