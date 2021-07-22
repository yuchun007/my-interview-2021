package com.yc.interview.java;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 *
 */
public class StringAndByteDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str1 = "abc中国";
        byte[] bytes = str1.getBytes();
        System.out.println(Arrays.toString(bytes));

        byte[] gbks = str1.getBytes("gbk");
        System.out.println(Arrays.toString(gbks));

        System.out.println("==============================================");
        String str2 = new String(bytes);
        String str3 = new String(gbks);
        String str4 = new String(gbks,"gbk");
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);




    }
}
