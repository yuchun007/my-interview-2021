package com.yc.interview.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;

/**
 * 同步队列：不存储元素，put一个，take一个，不然不能put下一个
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        ExecutorService executorService = ThreadUtil.newExecutor();
       BlockingQueue<Integer> integers = new SynchronousQueue<>();

        executorService.submit(()->{

            try {
                System.out.println(Thread.currentThread().getName() + "  put 1");
                integers.put(1);

                System.out.println(Thread.currentThread().getName() + "  put 2");
                integers.put(2);

                System.out.println(Thread.currentThread().getName() + "  put 3");
                integers.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                ThreadUtil.safeSleep(5000);
                System.out.println(Thread.currentThread().getName() + "  get" + integers.take());

                ThreadUtil.safeSleep(5000);
                System.out.println(Thread.currentThread().getName() + "  get" + integers.take());

                ThreadUtil.safeSleep(5000);
                System.out.println(Thread.currentThread().getName() + "  get" + integers.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}
