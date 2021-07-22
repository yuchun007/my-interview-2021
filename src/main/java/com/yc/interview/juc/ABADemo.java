package com.yc.interview.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);

    public static void main(String[] args) {
        System.out.println("=============以下是ABA问题的产生=========================");
        ExecutorService executorService = ThreadUtil.newExecutor();
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + "  " + atomicReference);
            atomicReference.compareAndSet(100,101);
            System.out.println(Thread.currentThread().getName() + "  " + atomicReference);
            atomicReference.compareAndSet(101,100);
            System.out.println(Thread.currentThread().getName() + "  " + atomicReference);
        });

        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + "  " + atomicReference);
            ThreadUtil.safeSleep(1000);
            boolean b = atomicReference.compareAndSet(100, 101);
            System.out.println(Thread.currentThread().getName() + " b value " + b + " " + atomicReference);
        });
        ThreadUtil.safeSleep(3000);

        System.out.println("=============以下是ABA问题的解决=========================");

        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + " value " + atomicStampedReference.getReference() + " version " + atomicStampedReference.getStamp());
            ThreadUtil.safeSleep(1000);
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+ 1);
            System.out.println(Thread.currentThread().getName() + " value " + atomicStampedReference.getReference() + " version " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+ 1);
            System.out.println(Thread.currentThread().getName() + " value " + atomicStampedReference.getReference() + " version " + atomicStampedReference.getStamp());
        });

        executorService.submit(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " value " + atomicStampedReference.getReference() + " version " + stamp);
            ThreadUtil.safeSleep(2000);
            boolean b = atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println("update result " + b);
            System.out.println(Thread.currentThread().getName() + " value " + atomicStampedReference.getReference() + " version " + stamp);
        });

        executorService.shutdown();
    }
}
