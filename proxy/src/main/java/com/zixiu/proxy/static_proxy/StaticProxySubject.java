package com.zixiu.proxy.static_proxy;

import com.zixiu.proxy.Subject;

/**
 * Author: Snail
 * Time:  2020/12/18 11:26 AM
 * FileName:  ProxySubject
 * 简介：代理角色
 * 需要实现抽象角色接口，是真实角色的代理，通过真实角色的业务逻辑方法来实现抽象方法，并可以附加自己的操作，将统一的流程控制都放到代理角色中处理
 */
public class StaticProxySubject implements Subject {

    private Subject realSubject;

    public StaticProxySubject(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public String request(String data) {
        doSthBefore();
        realSubject.request(data);
        doSthAfter();
        return data;
    }

    @Override
    public int response(int value) {
        return 0;
    }

    private void doSthBefore(){
        System.out.println("调用真实对象之前");
    }

    private void doSthAfter(){
        System.out.println("调用真实对象之后");
    }

}
