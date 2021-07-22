package com.yc.interview.aqs;


import javax.lang.model.element.VariableElement;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3种实现线程等待和唤醒的方法
 * 1.Object类种的wait（）方法和notify（）方法；
 * 2.java.util.concurrent (juc包)中Condition接口中的await（）和signal（）方法；
 * 3.LockSupport类中的park（）等待和unpark（）唤醒
 *======================================================
 * 1.wait和notify方法必须要在同步块或者方法里面且成对出现，先wait再notify
 * 2.await和signal方法必须有Lock且成对出现，先await再signal
 * 3.park和unpark方法不必放入锁块，且顺序可以颠倒（不会报错）
 * =====================================================
 * LockSupport：每个线程都有一个许可证（permit），这个凭证只能有一个，不可累积
 * 当调用park方法时，如果有凭证，则会直接消耗掉这个凭证，然后正常退出；
 *                  如果没有凭证，就必须阻塞等待凭证可用；
 * 当调用unpark方法时，会增加凭证，凭证最多一个，累加无效；
 */
public class LockSupportDemo {
    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        //1.测试Object中的wait方法和notify方法
        //   testObjectWaitAndNotify();

        //testConditionAwaitAndSignal();

        testLockSupportParkAndUnpark();
    }

    private static void testLockSupportParkAndUnpark() {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "=================================come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "=================================唤醒");
        },"threadA");
        a.start();

        Thread b = new Thread(() -> {
          LockSupport.unpark(a);
                System.out.println(Thread.currentThread().getName() + "=================================通知");
        },"threadB");
        b.start();
    }

    /**
     *  await()会使当前线程等待,同时会释放锁,当其他线程调用signal()时,线程会重新获得锁并继续执行。
     *   signal()用于唤醒一个等待的线程。
     */
    private static void testConditionAwaitAndSignal() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "=================================come in");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "=================================唤醒");
        },"threadA").start();

        new Thread(() -> {
            lock.lock();
            try{
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "=================================通知");
            }finally {
                lock.unlock();
            }
        },"threadB").start();
    }



    private static void testObjectWaitAndNotify() {
        new Thread(() -> {
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName() + "=================================come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "=================================唤醒");
            }
        },"threadA").start();

        new Thread(() -> {
            synchronized (objectLock){
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "=================================通知");
            }
        },"threadB").start();
    }
}
