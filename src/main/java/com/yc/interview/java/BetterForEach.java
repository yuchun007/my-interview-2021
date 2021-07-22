package com.yc.interview.java;

import com.yc.interview.entity.School;

import java.util.ArrayList;
import java.util.List;

/**
 * 对于for循环的优化
 *   1.外小内大原则
 *   2.提取与循环无关的表达式
 *   3.消除终止循环时的方法调用
 *   4.异常捕获消耗性能 放在循环外
 *   5.利用map结构优化遍历查询，如treeUtil
 *   6.如果循环中要新建类似对象，可以考虑clone（）
 *   =====================================
 *   注意：
 *   1，实验结果会因为电脑硬件不同而不同
 *   2，实验结果会因为 jdk 版本不同而不同
 *   3，实验结果会因为 jdk 自身的优化甚至出现完全相反的结果（这一点影响最大）
 *   4，如果不是数量级上的差别，那等于没有差别
 *   如果大家真的想测试一下，那么可以在 for 循环中添加一些业务逻辑，并且尽量避开 jdk 编译的自动优化。
 *
 *   System.nanoTime()
 *   返回正在运行的Java虚拟机的高分辨率时间源的当前值，以纳秒为单位。
 *   此方法只能用于测量经过的时间，并且与系统或挂钟时间的任何其他概念无关。
 *
 *    System.currentTimeMillis
 *    返回的是从1970.1.1 UTC 零点开始到现在的时间，精确到毫秒，平时我们可以根据
 *    System.currentTimeMillis来计算当前日期，星期几等，可以方便的与Date进行转换
 */
public class BetterForEach {
    public static void main(String[] args) throws CloneNotSupportedException {
//        testMethod1();
//        testMethod2();
//        testMethod3();
//        testMethod4();
//        testMethod5();
        testMethod6();
    }

    private static void testMethod6() throws CloneNotSupportedException {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            School school = new School();
            school.setName("name" + i);
        }
        System.out.println("未使用克隆: " + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        School school = new School();
        for (int i = 0; i < 100; i++) {
            School clone = school.clone();
            clone.setName("name" + i);
        }
        System.out.println("使用克隆: " + (System.nanoTime() - startTime));
    }

    private static void testMethod5() {
    }

    private static void testMethod4() {
        long stratTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            try {
            } catch (Exception e) {
            }
        }
        long endTime = System.nanoTime();
        System.out.println("在内部捕获异常耗时："+(endTime - stratTime));

        stratTime = System.nanoTime();
        try {
            for (int i = 0; i < 1000; i++) {
            }
        } catch (Exception e) {
        }
        endTime = System.nanoTime();
        System.out.println("在外部捕获异常耗时："+(endTime - stratTime));
    }

    private static void testMethod3() {
        long stratTime = System.nanoTime();
        int a = 1023;
        int b = 22344;
        for (int i = 0; i < 10000000; i++) {
            i=i*a*b;
        }
        long endTime = System.nanoTime();
        System.out.println("未提取耗时："+(endTime - stratTime));

        stratTime = System.nanoTime();
        int c = a*b;
        for (int i = 0; i < 10000000; i++) {
            i=i*c;
        }
        endTime = System.nanoTime();
        System.out.println("已提取耗时："+(endTime - stratTime));

    }

    private static void testMethod2() {
        long startTime = System.nanoTime();
        List<String> list = new ArrayList<>(1000);
        for (int i = 0; i < list.size() ; i++) {

        }
        System.out.println("循环终止调用方法用时： " + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        int size = list.size();
        for (int i = 0; i < size ; i++) {

        }
        System.out.println("不调用方法用时： " + (System.nanoTime() - startTime));
    }

    private static void testMethod1() {
        long startTime = System.nanoTime();
        for (int i = 0; i <10 ; i++) {
            for (int j = 0; j <100000 ; j++) {

            }
        }
        System.out.println("外小内大消耗时间： " + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        for (int i = 0; i < 100000 ; i++) {
            for (int j = 0; j < 10; j++) {

            }
        }
        System.out.println("外大内小消耗时间： " + (System.nanoTime() - startTime));
    }

}
