package com.yc.interview.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同一线程外层函数获得锁之后，内存递归函数仍然能获得该锁的代码，
 * 也即是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
       //testSynchronizd();
        testReentrantLock();
    }

    private static void testReentrantLock() {
        ExecutorService executorService = ThreadUtil.newExecutor();
        executorService.submit(()->{
            new Phone().m1();
        });
        ThreadUtil.safeSleep(1000);
        executorService.submit(()->{
            new Phone().m1();
        });
        executorService.shutdown();
    }

    private static void testSynchronizd() {
        ExecutorService executorService = ThreadUtil.newExecutor();
        executorService.submit(()->{
            new Phone().sendMsg();
        });
        executorService.submit(()->{
            new Phone().sendMsg();
        });
        executorService.shutdown();
    }
}

class Phone{
    private Lock lock = new ReentrantLock();
    public synchronized void sendMsg(){
        System.out.println(Thread.currentThread().getName() + "  send msg");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName() + "  send e-mail");
    }

    public void m1(){
        lock.lock();
        try {
            System.out.println("=================m1");
            m2();
        }finally {
            lock.unlock();
        }

    }
    public void m2(){
        lock.lock();
        try {
            System.out.println("=================m2");
        }finally {
            lock.unlock();
        }

    }


}