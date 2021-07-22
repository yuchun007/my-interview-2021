package com.yc.interview.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 利用适配模式
 * public FutureTask(Callable<V> callable) {
 * public class FutureTask<V> implements RunnableFuture<V> {
 * public interface RunnableFuture<V> extends Runnable, Future<V> {
 *
 * 注意：1.futureTask.get()放在最后执行，因为获得Callable线程的计算结果，
 *      如果没有计算完成就要去强求，会导致堵塞，等待其值计算完成；
 *      2.可以配合futureTask.isDone方法使用,如下面代码；
 *      3.同一个futureTask只执行一次。
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Mythread3());
        new Thread(futureTask," my thread1 ").start();
        //同一个futureTask只执行一次
        new Thread(futureTask," my thread2 ").start();

//        while(!futureTask.isDone()){
//
//        }

        System.out.println("=========result: " + futureTask.get());
    }
}
class Mythread1 extends Thread{

}
class Mythread2 implements Runnable{

    @Override
    public void run() {

    }
}

/**
 * 带返回值的，抛异常的
 */
class Mythread3 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " 进入call方法====");
        return 1024;
    }
}