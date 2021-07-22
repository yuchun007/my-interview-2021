package com.yc.interview.twosum;


/**
 * 字符串反转算法
 * 参考以下方法
 * stringBuilder.reverse()  StringBuffer也有这个方法
 */
public class StringReverseDemo {
    public static void main(String[] args) {
        String str = "abcdefgg";
        int n = str.length() - 1;
        char[] chars = str.toCharArray();
        for (int j = (n-1) >> 1; j >= 0; j--) {
            int k = n - j;
            char cj = chars[j];
            char ck = chars[k];
            chars[j] = ck;
            chars[k] = cj;
        }
        System.out.println(new String(chars));

    }
}
