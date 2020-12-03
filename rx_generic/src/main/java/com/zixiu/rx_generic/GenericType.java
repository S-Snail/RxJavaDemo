package com.zixiu.rx_generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Author: Snail
 * Time:  2020/12/2 3:25 PM
 * FileName:  GenericType
 * 简介：获取泛型类型测试类
 */
public class GenericType<T> {

    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public static void main(String[] args) {
//        GenericType<String> genericType = new GenericType<String>(){};
//        Type genericSuperclass = genericType.getClass().getGenericSuperclass();
//        Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
//        System.out.println("泛型type：" + type);

        GenericType<TestType> testTypeGenericType = new GenericType<TestType>(){};
        Type superclass = testTypeGenericType.getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
        System.out.println("type = " + type);
    }

    static final class TestType{

    }

}
