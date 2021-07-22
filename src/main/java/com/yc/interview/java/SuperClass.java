package com.yc.interview.java;

import java.io.File;

public class SuperClass {
    public static void sayHello(){

        System.out.println("由 superClass 说你好");
    }
}
class SubClass extends SuperClass{
    public static void sayHello(){
        System.out.println("由 SubClass 说你好");
    }
    public static void main(String[] args) {
    }
}