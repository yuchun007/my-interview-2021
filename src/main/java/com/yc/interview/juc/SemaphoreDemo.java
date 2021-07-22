package com.yc.interview.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * 信号量或信号灯，类似停车场中的车位指示灯，当红灯时，表示车位已占用，当绿灯时表示车位可使用
 *
 * 演示案例：6量车使用三个车位
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = ThreadUtil.newExecutor();
        for (int i = 1; i < 7; i++) {
            executorService.submit(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "  占用车位，停车");
                    ThreadUtil.safeSleep(new Random().nextInt(10000));
                    System.out.println(Thread.currentThread().getName() + "  离开车位，缴费出库");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            });
        }
        executorService.shutdown();
    }
}
