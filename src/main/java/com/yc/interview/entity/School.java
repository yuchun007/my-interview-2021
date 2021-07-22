package com.yc.interview.entity;

public class School implements Cloneable{
    private String name;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    @Override
    public School clone() throws CloneNotSupportedException{
        return (School)super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                '}';
    }
}
