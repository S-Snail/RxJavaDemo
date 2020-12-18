package com.zixiu.proxy.static_proxy;

import com.zixiu.proxy.Subject;

/**
 * Author: Snail
 * Time:  2020/12/18 11:23 AM
 * FileName:  RealSubject
 * 简介：真实角色
 * 需要实现抽象角色接口，定义了真实角色所需要实现的业务逻辑，以便供代理角色调用。也就是真正的业务逻辑在此
 */
public class RealSubject implements Subject {
    @Override
    public String request(String data) {
        System.out.println("RealSubject - data = " + data);
        return data;
    }

    @Override
    public int response(int value) {
        return value;
    }
}
