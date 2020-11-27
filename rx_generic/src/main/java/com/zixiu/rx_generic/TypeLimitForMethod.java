package com.zixiu.rx_generic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Author: Snail
 * Time:  2020/11/27 4:27 PM
 * FileName:  TypeLimitForMethod
 * 简介：限定泛型类型变量
 * 类型变量限定 - 方法
 */
public class TypeLimitForMethod {
    /*
     * 限定类型使用extends关键字指定
     * 可以使用类或者接口，类放在前面，后面的用&符号分割
     */
    public <T extends ArrayList & Comparable & Serializable> void Test(T t) {

    }

    public static <T extends Comparable<T>> T getMin(T a, T b) {
        return a.compareTo(b) < 0 ? a : b;
    }

    public static void main(String[] args) {
        System.out.println("获取最小值" + getMin(1,2));
        System.out.println("获取最小值" + getMin("a","b"));

    }

}
