package com.yc.interview.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    public static void main(String[] args) {
        when_memory_enough();

        System.out.println();
        System.out.println("===============以下为内存不足时===============");
        System.out.println();

        whem_memory_notEnough();
    }

    private static void whem_memory_notEnough() {
        Object obj1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(objectSoftReference.get());

        System.out.println("======================");
        obj1 = null;
        try {
            byte[] bytes = new byte[30*1024*1024];
        }finally {
            System.out.println(obj1);
            System.out.println(objectSoftReference.get());
        }
    }

    private static void when_memory_enough() {
        Object obj1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(objectSoftReference.get());

        System.out.println("======================");

        obj1 = null;
        System.out.println(obj1);
        System.out.println(objectSoftReference.get());

    }
}
