package com.yc.interview.juc.prodconsumer;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 利用阻塞队列实现生产者和消费者模式
 */
public class ProdConsumer_BlockQueue {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource(new ArrayBlockingQueue(5));
        ExecutorService executorService = ThreadUtil.newExecutor();
        executorService.submit(()->{
            try {
                dataSource.prod();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.submit(()->{
            try {
                dataSource.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ThreadUtil.safeSleep(5000);
        dataSource.stop();
        executorService.shutdown();
    }
}

class DataSource {
    /**
     *  一定要加上volatile关键字
     */
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue blockingQueue = null;

    public DataSource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void prod() throws Exception {
        int data = 0;
        boolean result;
        while (flag) {
            data = atomicInteger.incrementAndGet();
            result = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + " 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + " 插入队列" + data + "失败");
            }
            ThreadUtil.safeSleep(1000);
        }
        System.out.println("=====================================停止生产");

    }

    public void consumer() throws Exception {
        Integer value;
        while (flag) {
            value = (Integer) blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (value == null || value == 0) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + " 超过两秒没有获得数据，消费结束");
                return;
            }
            System.out.println("============================消费队列" + value + "成功");
        }
    }

    public void stop(){
        this.flag = false;
    }
}