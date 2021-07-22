package com.yc.interview.juc;

import cn.hutool.core.thread.ThreadUtil;
import com.yc.interview.entity.CountryEnum;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 倒数计数
 *
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //closeDoorDemo();
        sevenCountryStory();
    }

    /**
     * 注意枚举的应用，特别是枚举中的遍历方法
     */
    private static void sevenCountryStory() throws InterruptedException{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        ExecutorService executorService = ThreadUtil.newExecutor(6);
        for (int i = 1; i < 7 ; i++) {
            int finalI = i;
            executorService.submit(()->{
                System.out.println(CountryEnum.forEach_CountryEnum(finalI).getReName() + " 被灭了");
                countDownLatch.countDown();
            });
        }
        executorService.shutdown();
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 秦统一六国，一统天下");
    }

    /**
     *  demo：6个学生出教室，班长再锁门
     */
    private static void closeDoorDemo() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        ExecutorService executorService = ThreadUtil.newExecutor(6);
        for (int i = 0; i < 6 ; i++) {
            int finalI = i;
            executorService.submit(()->{
                System.out.println(finalI + " 号同学出教室");
                countDownLatch.countDown();
            });
        }
        executorService.shutdown();
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 班长锁门回家");

    }
}
