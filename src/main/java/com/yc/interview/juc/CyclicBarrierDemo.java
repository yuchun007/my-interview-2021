package com.yc.interview.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

/**
 * 可循环的屏障,线程组被阻拦在屏障处，直到最后一个线程抵达才会打开大门，执行线程
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("==========集齐龙珠，召唤神龙");
        });
        ExecutorService executorService = ThreadUtil.newExecutor();
        for (int i = 1; i < 8; i++) {
            int num = i;
            executorService.submit(()->{
                System.out.println("第" + num + "龙珠集齐");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

        }

        executorService.shutdown();
    }
}
