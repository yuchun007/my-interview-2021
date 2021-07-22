package com.yc.interview.java;

import java.sql.SQLOutput;

/**
 * 自增的堆栈操作
 * 局部变量表
 * 操作数栈
 * ===============================================
 * 赋值=，最后计算的
 * =右边的从左到右加载值依次压入操作数栈
 * 实际先算哪个，看运算符的优先级
 * 自增、自减操作都是直接修改变量的值，不经过操作数栈
 * 最后的赋值之前，临时结果（入下例中++i*i++的结果）也是存储在操作数栈中
 */
public class SelfIncreasing {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }

}
