package com.yc.interview.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.volatile可见性演示
 * 2.不保证原子性演示(原子性，不可分割性，即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整，要么同时成功，要么同时失败)
 *   会写覆盖
 * 如何解决原子性：
 *      * 2.1.加syn
 *      * 2.2.atomic 直接使用juc下的原子类
 * 3.有序性防止指令重排演示
 */
class VolatileDemo {
    public static void main(String[] args) {
        testSeeOkByValotile();

       // testAtomicity();

    }

    /**
     * volatile不保证原子性演示
     *
     */
    private static void testAtomicity() {
        ExecutorService executorService = ThreadUtil.newExecutor();
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            executorService.submit(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            });
        }
       executorService.shutdown();

        //上面所有线程执行完成后，才往下走，默认有gc线程
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println("int type ,finally number value： " + myData.number);
        System.out.println("AtomicInterger type ,finally atomicInterger value： " + myData.atomicInteger);

    }

    /**
     * volatile可见性演示
     */
    public static void testSeeOkByValotile(){
        ExecutorService executorService = ThreadUtil.newExecutor();
        MyData myData = new MyData();
        executorService.submit(()->{System.out.println(Thread.currentThread().getName() + ":come in  " + myData.number);
            ThreadUtil.safeSleep(3000L);
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + ":update value number " + myData.number);
        });
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + ":get number value " + myData.number);
            while (myData.number == 0){}
            System.out.println(Thread.currentThread().getName() + ":get number value " + myData.number);
        });
        executorService.shutdown();
    }

    static class MyData{
        private volatile int number = 0;
        public void addTo60(){
            number = 60;
        }

        //number++在多线程下是非线程安全的，volatile不能保证原子性
        public void addPlusPlus(){
            ++number;
        }

        //默认值是0
        AtomicInteger atomicInteger = new AtomicInteger();
        //atomic 原子类
        public void addMyAtomic(){
            atomicInteger.getAndIncrement();
        }
    }

}
