package com.yc.interview.entity;


public class Student implements Cloneable{
    private int age;
    private String str1;
    private School school;

    public Student(int age, String str1, School school) {
        this.age = age;
        this.str1 = str1;
        this.school = school;
    }

    @Override
    public Student clone() throws CloneNotSupportedException{
        return (Student)super.clone();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", str1='" + str1 + '\'' +
                ", school=" + school +
                '}';
    }
}
