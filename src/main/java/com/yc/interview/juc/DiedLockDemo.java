package com.yc.interview.juc;



import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.thread.ThreadUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 死锁演示：A持有A锁，抢B锁  B持有B锁，抢A锁
 * jps jstack 等命令
 */
public class DiedLockDemo {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("demo-pool-").build();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                3,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );
        String lockA = "lockA";
        String lockB = "lockB";
        Mydata mydata = new Mydata(lockA, lockB);
        Mydata mydata1 = new Mydata(lockB, lockA);
        threadPool.submit(()->{
            mydata.testMethod();
        });
        threadPool.submit(()->{
            mydata1.testMethod();
        });



        threadPool.shutdown();
    }
}
class Mydata{
    private String lockA;
    private String lockB;

    public Mydata(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void testMethod(){
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + " come in and have " + lockA);
            ThreadUtil.safeSleep(1000);
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + " come in and have " + lockB);
            }
        }
    }
}