package com.yc.interview.java;


/**
 * 1.main方法所在类需要先加载和初始化
 * 2.一个类初始化就是执行<clinit>()方法,<clint>()方法由 静态类变量显示赋值代码(如下例j)和静态代码块组成(两者从上到下执行)
 *   <clint>()方法只执行一次
 * 3.实例化就是执行<init>()方法, <init>()方法可能重载有多个,有几个构造器就有几个<init>()方法
 *   <init>()方法由 a.非静态实例变量显示赋值代码 b.非静态代码块(a b 按照从上到下的顺序执行) c.对应构造器代码(最后执行) 组成
 *   <init>()方法首行是super()或super(实参列表),即对应父类的<init>()方法
 *   每次创建实例对象,调用对应得构造器,执行的就是对应的<init>()方法
 */
public class ClassLoadOrder {
    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println("===================");
        Son s2 = new Son();
    }

    static class Father{
        private int i = test();
        private static int j = method();

        static {
            System.out.println("1");
        }
        Father(){
            System.out.println("2");
        }
        {
            System.out.println("3");
        }

        public int test(){
            System.out.println("4");
            return 1;
        }
        public static int method(){
            System.out.println("5");
            return 1;
        }
    }

    static class Son extends Father{
        private int i = test();
        private static int j = method();

        static {
            System.out.println("6");
        }
        Son(){
            //super(); 写或不写,此方法都存在,即在子类构造中一定会调用父类构造器
            System.out.println("7");
        }
        {
            System.out.println("8");
        }

        @Override
        public int test(){
            System.out.println("9");
            return 1;
        }
        public static int method(){
            System.out.println("10");
            return 1;
        }
    }
}
