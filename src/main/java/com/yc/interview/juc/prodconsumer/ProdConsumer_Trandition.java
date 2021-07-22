package com.yc.interview.juc.prodconsumer;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock实现生产者消费者模式
 * 线程 操纵（方法） 资源类
 * 判断（while） 干活 通知
 * 防止虚假召唤机制 while
 */
public class ProdConsumer_Trandition {
    public static void main(String[] args) {
        ExecutorService executorService = ThreadUtil.newExecutor();
        ShareData shareData = new ShareData();

        executorService.submit(()->{
            for (int i = 0; i < 5; i++) {
                shareData.increament();
            }
        });
        executorService.submit(()->{
            for (int i = 0; i < 5; i++) {
                shareData.decreament();
            }
        });

        executorService.shutdown();
    }

}

class ShareData{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increament(){
        lock.lock();
        try {
            //1判断,此处判断官方建议用while 如果用if再加几个线程操作此资源类会报错
            while (num != 0){
                //等待，不能生产,此时挡前线程阻塞，释放锁
                condition.await();
            }
            num++;
            System.out.println("prod num value : " + num);
            //此时唤醒其他线程，已经生产好产品了
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decreament(){
        lock.lock();
        try {
            while (num == 0){
                condition.await();
            }
            num--;
            System.out.println("comsumer num value : " + num);
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}