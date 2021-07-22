package com.yc.interview.aqs;

import java.security.spec.MGF1ParameterSpec;

/**
 * 可重入锁：可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不会发生死锁，这样的锁叫做可重入锁
 *         或者说同一个线程可以多次获得同一把锁
 * 隐式锁synchronized 默认是可重入锁
 * 显示锁Lock 也有ReentrantLock这样的可重入锁 加锁 解锁 两两匹配
 */
public class ReEnterLockDemo {
    private static Object object = new Object();

    public static void methodA(){
        new Thread(() -> {
            synchronized (object){
                System.out.println(Thread.currentThread().getName() + "\t" + "---------------外层调用");
                synchronized (object){
                    System.out.println(Thread.currentThread().getName() + "\t" + "---------------中层调用");
                    synchronized (object){
                        System.out.println(Thread.currentThread().getName() + "\t" + "---------------内层调用");
                    }
                }
            }
        },"threadA").start();
    }

    public synchronized void m1(){
        System.out.println("-----------m1 外层");
        m2();
    }
    public synchronized void m2(){
        System.out.println("-----------m1 中层");
        m3();
    }
    public synchronized void m3(){
        System.out.println("-----------m1 内层");

    }
    public static void main(String[] args) {
        methodA();
        System.out.println("===========================");
        new ReEnterLockDemo().m1();
    }
}
