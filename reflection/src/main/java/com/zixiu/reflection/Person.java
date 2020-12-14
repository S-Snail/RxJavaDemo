package com.zixiu.reflection;

/**
 * Author: Snail
 * Time:  2020/12/14 3:19 PM
 * FileName:  Person
 * 简介：
 */
public class Person {
    public static String CLASS_NAME = "com.zixiu.reflection.Person";

    private int age;
    private String name;

    public Person() {
    }

    public Person(int age) {
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(int age, String name) {
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



}
