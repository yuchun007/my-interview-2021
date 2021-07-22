package com.yc.interview.juc;

/**
 * synchronized和ReentrantLock之间的区别
 * 。。。
 * ReentrantLock可以实现精确唤醒，可以中断，lock相对于synch的优势
 * =================================================================
 * 示例：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * AA打印5次 BB打印10次 CC打印15次
 * 紧接着
 *AA打印5次 BB打印10次 CC打印15次
 * ....
 * 来10轮
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {

    }
}
