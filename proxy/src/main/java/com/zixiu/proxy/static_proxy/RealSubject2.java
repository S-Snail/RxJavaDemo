package com.zixiu.proxy.static_proxy;

import com.zixiu.proxy.Subject;

/**
 * Author: Snail
 * Time:  2020/12/18 5:44 PM
 * FileName:  RealSubject2
 * 简介：
 */
public class RealSubject2 implements Subject {
    @Override
    public String request(String data) {
        System.out.println("RealSubject2 - data = " + data);
        return data;
    }

    @Override
    public int response(int value) {
        return 0;
    }
}
