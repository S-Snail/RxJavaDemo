package com.zixiu.proxy;

import com.zixiu.proxy.dynamic_proxy.DynamicProxySubjectFactory;
import com.zixiu.proxy.static_proxy.StaticProxySubject;
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
        Subject proxySubject = new StaticProxySubject(realSubject);
        proxySubject.request("静态代理类调用了");
        /**
         * 动态代理
         * 优点：只需要一个静态代理类，就可以解决创建多个静态代理类的问题。避免重复，多余代码，有更强的灵活性
         * 缺点：效率低：相比静态代理中直接调用目标对象的方法，动态代理则需要通过Java反射机制从而间接调用目标对象方法
         *      应用局限性：因为Java的单继承特性（每个代理类都继承了Proxy类），即只能针对接口（多实现）创建代理类，不能针对类创建代理类
         */
        System.out.println("-------动态代理Demo2-------");
        Subject realDynamicProxySubject = (Subject) new DynamicProxySubjectFactory(new RealSubject()).getProxyInstance();
        realDynamicProxySubject.request("动态代理类调用了");
        realDynamicProxySubject.response(520);
    }
}
