package com.zixiu.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Jdk动态代理
 */
public class JdkDynamicProxy {

    interface Subject{
        void doSomething();
    }

    public static class RealSubject implements Subject{

        @Override
        public void doSomething() {
            System.out.println("do something()");
        }
    }

    public static class DynamicProxy implements InvocationHandler{

        private Object target;

        public DynamicProxy(Object target) {
            this.target = target;
        }

        public <T> T getProxy(){
            return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("do something before");
            //实现代理对象实现真实对象的方法
            Object result = method.invoke(target, args);
            System.out.println("do something after");
            return result;
        }
    }

    public static void main(String[] args) {
        Subject subject = new DynamicProxy(new RealSubject()).getProxy();
        subject.doSomething();
    }

}
