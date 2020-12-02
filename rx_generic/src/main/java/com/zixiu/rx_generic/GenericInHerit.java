package com.zixiu.rx_generic;

/**
 * Author: Snail
 * Time:  2020/11/30 3:29 PM
 * FileName:  GenericInHerit
 * 简介：泛型类型继承规则
 * 1，对于泛型参数是继承关系的泛型类之间是没有继承关系的
 * 2，泛型类可以继承其他泛型类
 * 3，泛型类的继承关系在使用中同样会受到泛型类型的影响
 */
public class GenericInHerit<T> {

    private T data1;
    private T data2;

    public T getData1() {
        return data1;
    }

    public void setData1(T t) {
        this.data1 = t;
    }

    public T getData2() {
        return data2;
    }

    public void setData2(T t) {
        this.data2 = t;
    }

    private static class SubGenericInHerit<T> extends GenericInHerit<T> {

    }

    private static class Father {

    }

    private static class Son extends Father {

    }


    public static <V> void setData2(GenericInHerit<Father> data2){

    }

    public static void main(String[] args) {
        Father father = new Father();
        Son son = new Son();

        GenericInHerit<Father> fatherGenericInHerit = new GenericInHerit<>();
        GenericInHerit<Son> sonGenericInHerit = new GenericInHerit<>();
        SubGenericInHerit<Father> fatherSubGenericInHerit = new SubGenericInHerit<>();
        SubGenericInHerit<Son> sonSubGenericInHerit = new SubGenericInHerit<>();

        //对于传递的泛型参数是继承关系的泛型类之间是没有继承关系的
        father = new Son();
//        fatherGenericInHerit = new GenericInHerit<Son>();

        //泛型类可以继承其他泛型类
        fatherGenericInHerit = new SubGenericInHerit<Father>();

        //泛型类的继承关系在使用中同样会受到泛型类型的影响
        setData2(fatherGenericInHerit);
//        setData2(sonGenericInHerit);
        setData2(fatherSubGenericInHerit);
//        setData2(sonGenericInHerit);

    }



}
