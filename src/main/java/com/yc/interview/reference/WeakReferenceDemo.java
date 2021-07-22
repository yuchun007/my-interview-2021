package com.yc.interview.reference;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        WeakReference<Object> objectWeakReference = new WeakReference<>(obj1);

        System.out.println(obj1);
        System.out.println(objectWeakReference.get());

        System.out.println("======================================");

        obj1 = null;
        System.gc();
        System.out.println(obj1);
        System.out.println(objectWeakReference.get());
    }
}
