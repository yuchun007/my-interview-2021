package com.yc.interview.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：
 *   读-读可以共存
 *   读-写不能共存
 *   写写不能共存
 *
 *   写操作：独占+原子操作
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ExecutorService executorService = ThreadUtil.newExecutor(5);
        Cache cache = new Cache();
        for (int i = 0; i < 5 ; i++) {
            int finalI = i;
            executorService.submit(()->{
                cache.put(String.valueOf(finalI), finalI);
                System.out.println(Thread.currentThread().getName() + cache.get(String.valueOf(finalI)));
            });
        }
        ThreadUtil.safeSleep(1000);
        executorService.submit(()->{
            //获取所有的堆栈信息
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            Set<Map.Entry<Thread, StackTraceElement[]>> entries = allStackTraces.entrySet();
            for(Map.Entry<Thread, StackTraceElement[]> entry : entries){
                Thread key = entry.getKey();
                StackTraceElement[] value = entry.getValue();
                System.out.println("[ Thread name is " + key.getName() + "]");
                for(StackTraceElement s : value){
                    System.out.println(s.toString());
                }
            }
        });

        executorService.shutdown();
    }

}
class Cache{
    private ReadWriteLock lock =  new ReentrantReadWriteLock();
    private volatile Map<String,Object> map = new ConcurrentHashMap();

    public void put(String key,Object obj){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入: " + obj );
            ThreadUtil.safeSleep(300);
            map.put(key,obj);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }

    }

    public Object get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "输出key: " + key );
            ThreadUtil.safeSleep(300);
            return map.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
        return null;
    }
}