package com.yc.interview.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS ==>比较并交换 compareAndSwap,它是cpu的并发原语；功能是判断内存中的某个位置的值是否为预期值，如果是则更改为新的值，这个过程是原子性的
 *     CAS并发原语体现在java语言中就是sun.misc.Unsafe类中的各个方法；掉调Unsafe类中的CAS方法，JVM会帮我们实现CAS汇编指令。这是一种完全依赖硬件的功能
 * 通过它实现了原子操作。再次强调，由于CAS是一种系统原语，原语属于操作系统用于范畴，是由若干指令组成的，用于完成某个功能的一个过程，并且原语的执行必须是连续的，
 * 在执行过程中不允许被中断，也就是说CAS是一条CPU的原子指令，不会造成所谓的数据不一致问题。
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2019) + "  atomicInteger value: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "  atomicInteger value: " + atomicInteger.get());
    }
}
