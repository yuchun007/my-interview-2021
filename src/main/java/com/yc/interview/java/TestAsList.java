package com.yc.interview.java;

import java.util.Arrays;

public class TestAsList {
    public static void main(String[] args) {
        int[] arr = {1,2,3};

        //基本数据类型，aslist识别为
        System.out.println(Arrays.asList(arr).size());

        System.out.println("==========================================");

        String[] star = {"1","2","3"};
        System.out.println(Arrays.asList(star).size());

        int oldCapacity = 9;
        System.out.println(oldCapacity >> 1);
        System.out.println(oldCapacity << 1);
    }
}
