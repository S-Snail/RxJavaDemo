package com.zixiu.proxy;

import com.zixiu.proxy.dynamic_proxy.DynamicProxySubjectFactory;
import com.zixiu.proxy.static_proxy.ProxySubject;
import com.zixiu.proxy.static_proxy.RealSubject;

/**
 * Author: Snail
 * Time:  2020/12/18 11:15 AM
 * FileName:  Main
 * 简介：
 */
public class ProxyMain {

    public static void main(String[] args) {
        /**
         * 静态代理
         * 缺点：违反开闭原则，导致扩展和可维护性差、
         * 1、一对一（一个真实对象对应一个代理对象）会出现代理对象量多，代码量大，从而导致代码量大，可维护性差
         * 2、一对多（一个代理对象对应多个真实对象）会导致代理对象可扩展性差
         */
        System.out.println("-------静态代理Demo1-------");
        Subject realSubject = new RealSubject();
        Subject proxySubject = new ProxySubject(realSubject);
        proxySubject.request("静态代理类调用了");
        /**
         * 动态代理
         */
        System.out.println("-------动态代理Demo2-------");
        Subject realDynamicProxySubject = (Subject) new DynamicProxySubjectFactory(new RealSubject()).getProxyInstance();
        realDynamicProxySubject.request("动态代理类调用了");
        realDynamicProxySubject.response(520);
    }
}
