package com.yc.interview.juc.prodconsumer;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用Object中wait（）和notify（）实现生产者消费者模式
 */
public class ProdConsumer_OldOne {
    public static void main(String[] args) {
        ExecutorService executorService = ThreadUtil.newExecutor();
        ShareData1 shareData = new ShareData1();

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

class ShareData1{
    private Integer num = 0;

    public void increament()  {
        try {
            synchronized (this){
                //1判断,此处判断官方建议用while
                // 如果用if再加几个线程操作此资源类会报错
                while (num != 0){
                    //等待，不能生产,此时挡前线程阻塞，释放锁
                    this.wait();
                }
                num++;
                System.out.println("prod num value : " + num);
                //此时唤醒其他线程，已经生产好产品了
                this.notifyAll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void decreament(){
        try {
            synchronized (this) {
                //1判断,此处判断官方建议用while 如果用if再加几个线程操作此资源类会报错
                while (num == 0) {
                    //等待，不能生产,此时挡前线程阻塞，释放锁
                    this.wait();
                }
                num--;
                System.out.println("comsumer num value : " + num);
                //此时唤醒其他线程，产品已经被消费
                this.notifyAll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
