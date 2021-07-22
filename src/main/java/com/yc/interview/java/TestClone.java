package com.yc.interview.java;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yc.interview.entity.School;
import com.yc.interview.entity.Student;

public class TestClone {


    public static void main(String[] args) throws CloneNotSupportedException {
        //浅拷贝 基本数据类型及封装类型还有String都是深拷贝，其他引用对象拷贝内存地址（浅拷贝）
        Student student = new Student(18,"aaa",new School("yali"));
        System.out.println(student.toString());
        Student clone = student.clone();
        System.out.println(clone.toString());
        clone.setAge(15);
        clone.setStr1("bbb");
        clone.getSchool().setName("changjun");
        System.out.println(student.toString());
        System.out.println(clone.toString());

        System.out.println("======================================================");
        //深拷贝 实现方式一，重写clone（）方法 二、利用序列化和反序列化
        JSONObject parse = JSONUtil.parseObj(student);
        Student deepClone = JSONUtil.toBean(parse, Student.class);
        deepClone.getSchool().setName("ccc");
        System.out.println(student.toString());
        System.out.println(deepClone.toString());


    }


}
