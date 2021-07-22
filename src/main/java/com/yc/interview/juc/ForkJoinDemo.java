package com.yc.interview.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分支合并框架
 * 演示：分支求和
 *
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveTask 递归任务
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        MySum mySum = new MySum(0, 100);

        ForkJoinTask<Integer> submit = pool.submit(mySum);
        System.out.println(submit.get());
        pool.shutdown();
    }
}
class MySum extends RecursiveTask<Integer> {
    private final Integer ADJUST_VALUE = 10;
    private Integer begin;
    private Integer end;
    private Integer result = 0;

    public MySum(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int totel = 0;
        if ((end - begin) < ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                totel += i;
            }
            return totel;
        }else {
            int mid = (end + begin) >> 1;
            System.out.println("mid value: " + mid);
            MySum mySum1 = new MySum(begin, mid);
            MySum mySum2 = new MySum(mid + 1, end);
            mySum1.fork();
            mySum2.fork();
            totel = mySum1.join() + mySum2.join();
        }
        return totel;
    }
}