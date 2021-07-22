package com.yc.interview.jvm;

public class 查看CPU情况 {
    public static void main(String[] args) {
        //返回jvm虚拟机可用核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
        //当前JVM空闲内存
        System.out.println(Runtime.getRuntime().freeMemory());
        //返回java虚拟机试图使用的最大内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("-Xmx" + maxMemory+ " 字节 " + maxMemory/1024/1024 + "MB");
        //返回java虚拟机中的总内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("-Xms" + totalMemory + " 字节 " + totalMemory/1024/1024 + "MB");

    }
}
