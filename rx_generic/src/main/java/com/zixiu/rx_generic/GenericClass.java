package com.zixiu.rx_generic;

/**
 * Author: Snail
 * Time:  2020/11/27 1:47 PM
 * FileName:  GenericClass
 * 简介：
 */
public class GenericClass<T> {
    T data;

    public void setData(T t){
        this.data = t;
    }

    public T getData(){
        return data;
    }

}
