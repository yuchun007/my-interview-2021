package com.yc.interview.jvm;

import java.util.Random;

public class PrintGCDetailDemo {
    public static void main(String[] args) {
        String str ="I want test out of memory error";
        while (true){
            str +=new Random().nextInt(888888888) + new Random().nextInt(999999999);
            str.intern();
        }

    }
}
