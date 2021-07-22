package com.yc.interview.reference;

public class ReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = obj1;
        System.out.println(obj1);
        System.out.println(obj2);

        System.out.println("==================");

        obj1 = null;
        System.gc();
        System.out.println(obj1);
        System.out.println(obj2);
    }
}
