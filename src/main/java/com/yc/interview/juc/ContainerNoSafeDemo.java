package com.yc.interview.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合类不安全的问题
 * ArrayList HashSet（底层即HashMap,其add方法添加元素为map的key，此时map中的value是一个常量（=new Object（），不用关心））
 * HashMap 线程不安全 用ConcurrentHashMap
 *   1.现象  ：java.util.ConcurrentModificationException
 *   2.解决方案： 2.1 用Vector类线程安全（加了锁，效率低）；
 *               2.2 用 Collections.synchronizedList(new ArrayList<>());
 *               2.3 用juc中的 CopyOnWriteArrayList<>()； 写时复制
 *  写时复制，
 *     CopyOnWrite容器即写时复制容器，往一个容器中添加元素时，不直接往当前容器object[]添加，而是先将当前容器object[]进行copy，
 *     复制出一个新的object[]容器newElements，然后往newElements中添加元素，添加完元素后，再将原容器的引用指向新的容器setArray（newElements）；
 *     这样做的好处是可以对CopyOnWrite容器进行并发读，而不需要加锁，因为当前容器不会添加任何元素。所以copyOnWrite是一种读写分离的思想，读和写不同的容器
 *  源码如下
 *  public boolean add(E e) {
 *         final ReentrantLock lock = this.lock;
 *         lock.lock();
 *         try {
 *             Object[] elements = getArray();
 *             int len = elements.length;
 *             Object[] newElements = Arrays.copyOf(elements, len + 1);
 *             newElements[len] = e;
 *             setArray(newElements);
 *             return true;
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 */
public class ContainerNoSafeDemo {
    public static void main(String[] args) {
        //List<String> list = new CopyOnWriteArrayList<>();
        List<String> list = new ArrayList<>();
        //HashSet<Object> objects = new HashSet<>();
        //System.nanoTime(); 取当前时间

        for (int i = 0; i < 300; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }
    }
}
