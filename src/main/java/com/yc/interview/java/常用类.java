package com.yc.interview.java;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 时间time参考time（package）   比较器 java.lang.Comparable  java.util.comparator
 *
 * 下面介绍System  java.lang.Math java.math.BigInteger和BigDecimal
 * ========================================================================
 * Math提供了一系列的静态方法用于科学计算，其方法参数和返回值类型一般为double型；
 *     abs   绝对值
 *     acos，asin，atan，cos，sin，tan 三角函数
 *     sqrt  平方根
 *     pow（double a，double b） a的b次幂
 *     log 自然对数
 *     exp e为底指数
 *     max（double a，double b） 大
 *     min（double a，double b） 小
 *     random（） 返回0.0到1.0的随机数
 *     long round（double a）  double类型数据a转换为long型（四舍五入）
 *     toDegrees（double angrad） 弧度->角度
 *     toRadians（double angdeg） 角度 -> 弧度
 *     ...
 */
public class 常用类 {

    /**
     * System 三个属性：in out err
     * System.gc()
     * System.exit(int status)
     * System.getProperty(String key)
     */
    @Test
    public void testSystem(){
        System.out.println(System.currentTimeMillis());

        String javaVersion = System.getProperty("java.version");
        System.out.println(javaVersion);

        String javaHome = System.getProperty("java.home");
        System.out.println(javaHome);

        String osName = System.getProperty("os.name");
        System.out.println(osName);

        String osVersion = System.getProperty("os.version");
        System.out.println(osVersion);

        String userName = System.getProperty("user.name");
        System.out.println(userName);

        String userHome = System.getProperty("user.home");
        System.out.println(userHome);

        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);

    }

    /**
     * BigInteger可以表示不可变的任意精度的整数，BigInteger还提供模运算。GCD计算，质数测试，素数生产。位操作以及一些其他操作
     *
     */
    @Test
    public void testMath(){
        BigInteger bigInteger = new BigInteger("2222222222222222222222222222222222222");
        System.out.println(bigInteger);

        BigDecimal bd1 = new BigDecimal("1234.12334");
        BigDecimal bg2 = new BigDecimal("11");
        //System.out.println(bd1.divide(bg2));除不尽就报错了
        System.out.println(bd1.divide(bg2, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(bd1.divide(bg2,25,BigDecimal.ROUND_HALF_UP));

    }
}
