package com.yc.interview.jvm;

import cn.hutool.core.thread.ThreadUtil;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JVM参数配置为： -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m  设置java堆外内存的峰值
 * 概念：当java进程花费98%以上的时间执行GC，但只恢复了不到2%的内存，且该动作连续重复了5次会抛出此错误。
 * 简单的说，就是应用程序已经基本耗尽了所有可用的内存，GC也无法回收
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

            try {
                while(true) {
                    list.add(String.valueOf(++i).intern());
                }
            }catch (Throwable e){
                System.out.println("==================== int i value : " + i);
                ThreadUtil.safeSleep(200);
                e.printStackTrace();
                throw e;
            }
     }
}
