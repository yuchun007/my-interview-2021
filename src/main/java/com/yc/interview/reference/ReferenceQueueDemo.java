package com.yc.interview.reference;

import cn.hutool.core.thread.ThreadUtil;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceQueueDemo {
    public static void main(String[] args) {
        Object object = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> objectWeakReference = new WeakReference<>(object, referenceQueue);

        System.out.println(object);
        System.out.println(objectWeakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("===================================");

        object = null;
        System.gc();
        ThreadUtil.safeSleep(500);

        System.out.println(object);
        System.out.println(objectWeakReference.get());
        System.out.println(referenceQueue.poll());


    }
}
