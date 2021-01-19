package com.snail.thread.ch01;

/**
 * 面试题：当一个线程在执行一个同步方法时，另外一个线程能否执行另外一个同步方法？
 * 推论：不能。理由，正在执行同步方法的线程持有当前对象锁，导致另一线程无法获取对象锁，所以无法执行另一个同步方法
 * 下面进行测试。
 * 测试结果：推论正确！
 */
public class TestSync {

    public  synchronized void methodA() {
        System.out.println("I am methodA()");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void methodB() {
        System.out.println("I am methodB()");
    }

}
