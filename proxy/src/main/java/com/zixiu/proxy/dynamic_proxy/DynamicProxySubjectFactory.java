package com.zixiu.proxy.dynamic_proxy;

import com.zixiu.proxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.xml.transform.Source;

/**
 * Author: Snail
 * Time:  2020/12/18 11:57 AM
 * FileName:  DynamicProxySubjectFactory
 * 简介：动态代理生成工厂
 */
public class DynamicProxySubjectFactory implements InvocationHandler {

    private Subject realSubject;

    public DynamicProxySubjectFactory(Subject realSubject) {
        this.realSubject = realSubject;
    }

    //通过Proxy获取动态代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        doSthBefore();
        Object proxyInstance = method.invoke(realSubject, objects);
        doSthAfter();
        return proxyInstance;
    }


    //前置处理
    private void doSthBefore(){
        System.out.println("调用真实对象之前");
    }

    //后置处理
    private void doSthAfter(){
        System.out.println("调用真实对象之后");
    }

}
