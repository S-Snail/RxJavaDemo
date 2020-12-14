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

    public void publicPersonMethod(){
        System.out.println("People的公有方法");
    }

    private void privatePersonMethod(){
        System.out.println("People的私有方法");
    }

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
        System.out.println("invoke Person's setAge() -> age = " + age);
    }

    public String getName() {
        System.out.println("invoke Person's getName() -> name = " + name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age){

    }



}
