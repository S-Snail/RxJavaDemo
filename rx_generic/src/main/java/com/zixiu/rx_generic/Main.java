package com.zixiu.rx_generic;

/**
 * Author: Snail
 * Time:  2020/11/27 11:42 AM
 * FileName:  Main
 * 简介：Java泛型（https://www.jianshu.com/p/986f732ed2f1）
 */
public class Main {

    private static int add(int a, int b) {
        System.out.println("int a + int b = " + (a + b));
        return a + b;
    }

    private static float add(float a, float b) {
        System.out.println("float a + float b = " + (a = b));
        return a + b;
    }

    private static double add(double a, double b) {
        System.out.println("double a + double b = " + (a = b));
        return a + b;
    }

    //泛型方法
    private static <T extends Number> double add(T a, T b) {
        System.out.println("T a + T b = " + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }

    public static void main(String[] args) {
        //泛型方法
        Main.add(1, 2);
        Main.add(1f, 2f);
        Main.add(1d, 2d);
        Main.add(Integer.valueOf(1), Integer.valueOf(2));
        Main.add(Float.valueOf(1), Float.valueOf(2));
        Main.add(Double.valueOf(1), Double.valueOf(2));

        //泛型类
        GenericClass<String> genericClass = new GenericClass<>();
        genericClass.setData("GenericClass");
        System.out.println("GenericClass output = " + genericClass.getData());

        //泛型接口
        ImplGenericInterface1<String> implGenericInterface1 = new ImplGenericInterface1<>();
        implGenericInterface1.setData("GenericInterface1_Impl");
        System.out.println(implGenericInterface1.getData());

    }
}
