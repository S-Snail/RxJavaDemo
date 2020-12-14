package com.zixiu.reflection;

/**
 * Author: Snail
 * Time:  2020/12/14 3:19 PM
 * FileName:  Child
 * 简介：
 */
public class Child extends Person{
    public static String CLASS_NAME = "com.zixiu.reflection.Child";
    private void privateChildMethod(){
        System.out.println("Child 的私有方法");
    }

    public void publicChildMethod(){
        System.out.println("Child 的公有方法");
    }

}
