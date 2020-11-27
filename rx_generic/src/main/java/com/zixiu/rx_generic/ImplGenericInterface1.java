package com.zixiu.rx_generic;

/**
 * Author: Snail
 * Time:  2020/11/27 2:02 PM
 * FileName:  ImplGenericInterface1
 * 简介：
 */
public class ImplGenericInterface1<T> implements GenericInterface<T> {

    private T data;

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }
}
