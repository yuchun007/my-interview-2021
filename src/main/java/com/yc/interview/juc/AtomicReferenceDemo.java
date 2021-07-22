package com.yc.interview.juc;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zhangsan = new User("zhangsan", 22);
        User lisi = new User("lisi", 25);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(zhangsan);
        System.out.println(userAtomicReference.compareAndSet(zhangsan,lisi) + "  user value" + userAtomicReference.get());
        System.out.println(userAtomicReference.compareAndSet(zhangsan,lisi) + "  user value" + userAtomicReference.get());

    }
}

class User{
    private String name;
    private int age;

    public User(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}