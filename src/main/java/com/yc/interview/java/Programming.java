package com.yc.interview.java;


public class Programming {
    public static void main(String[] args) {
        int n = 10;
        //System.out.println(f(n));

        System.out.println(m(n));
    }

    /**
     * 5个一级宝石能兑换2个二级宝石,以此类推,那么1个十级宝石需要多少个一级宝石？
     */
    private static int m(int n) {
        if (n <= 0){return 0;}
        if (n == 1){
            return 1;
        }
        return m(n-1)*6;
    }

    /**
     *有n步台阶,一次只能上1步或2步,共有多少种走法?
     */
    private static int f(int n) {
        if (n < 0){return 0;}
        if (n == 1 || n == 2){
            return n;
        }
        return f(n - 2) + f(n - 1);
    }
}
