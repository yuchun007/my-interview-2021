package com.yc.interview.java;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        //根节点加载器Bootstrap是C++语言编写的，无法获取，返回null
        System.out.println(obj.getClass().getClassLoader());
        //System.out.println(obj.getClass().getClassLoader().getParent());
        System.out.println("==========AppClassLoader加载CLASSPATH所指定的路径，可以用如下方法获取========================");
        System.out.println(System.getProperty("java.class.path"));

        System.out.println("=============================================");

        ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo();
        System.out.println(classLoaderDemo.getClass().getClassLoader());
        System.out.println(classLoaderDemo.getClass().getClassLoader().getParent());
        System.out.println(classLoaderDemo.getClass().getClassLoader().getParent().getParent());
    }
}
