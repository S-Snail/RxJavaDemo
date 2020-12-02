package com.zixiu.rx_generic;

/**
 * Author: Snail
 * Time:  2020/11/30 1:52 PM
 * FileName:  GenericRestrict1
 * 简介：泛型的约束和局限性
 */
public class GenericRestrict1<T> {

    private T data;

    /**
     * 不能实例化泛型类
     */
    public void setData() {
//        this.data = new T();
    }

    static class NormalClass {

    }

    /**
     * 静态变量或方法不能引用泛型类型变量
     */
//    private static T result;
//
//    public static T getResult(){
//        return result;
//    }

    /**
     * 静态方法是可以的
     */
    public static <K> K getKey(K k){
        return k ;
    }


    public static void main(String[] args) {
        GenericRestrict1<String> genericRestrict1 = new GenericRestrict1<>();
        GenericRestrict1<Integer> genericRestrict2 = new GenericRestrict1<>();
        GenericRestrict1<String> genericRestrict3 = new GenericRestrict1<>();

        NormalClass normalClassA = new NormalClass();
        NormalClass normalClassB = new NormalClass();

        //普通类型无法作为泛型类型
//        new GenericRestrict1<int>();

        //无法使用instanceof关键字判断泛型类的类型
//        if (genericRestrict1 instanceof GenericRestrict1<Integer>(){
//            return;
//        }

        //无法使用'=='判断两个泛型类的实例
//        if (genericRestrict1 == genericRestrict2){
//            return;
//        }

        /**
         * 泛型类的原生类型与所传递的泛型无关，无论传递什么类型，原生类是一样的
         */
        System.out.println("非泛型原声类比较:" + (normalClassA == normalClassB));
        System.out.println(genericRestrict1 == genericRestrict1);
        System.out.println(genericRestrict1.getClass() == genericRestrict2.getClass());
        System.out.println(genericRestrict1.getClass());
        System.out.println(genericRestrict2.getClass());

        //泛型数组可以声明，但无法实例化
        GenericRestrict1<String> genericRestrict11Array;
//        genericRestrict1 = new GenericRestrict1<>()[10];

    }

}
