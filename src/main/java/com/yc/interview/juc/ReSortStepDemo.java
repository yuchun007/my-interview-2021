package com.yc.interview.juc;

public class ReSortStepDemo {
    int a = 0;
    boolean flag = false;

    public void method1(){
        a = 1;              //语句1
        flag = true;        //语句2
    }

    /**
     * 多线程环境中线程交替执行，由于编译器的优化重排的存在，两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测；
     * 当指令重排，先执行语句2，再执行3，此时结果为5 而不是6
     */
    public void method2(){
        if (flag){
            a = a + 5;     //语句3
            System.out.println("===========result: " + a);
        }
    }
}
