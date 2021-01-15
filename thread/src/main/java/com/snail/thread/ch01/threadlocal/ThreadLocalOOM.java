package com.snail.thread.ch01.threadlocal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类说明：ThreadLocal造成的内存泄漏演示
 * 扩展：
 * 强引用：类似Object obj = new Object();栈中的引用obj指向堆中的Object实例，这种引用称为强引用
 * 软引用：要发生内存溢出，如果发现回收的内存不够，会将软引用所指向的堆中的对象内存回收掉
 * 弱引用：只要发生垃圾回收，所指向的堆上的实例，一定会回收
 * 虚引用：当要发生垃圾回收了，通知一下要发生回收的对象
 * <p>
 * 综上：
 * ThreadLocal发生内存泄漏的原因是：ThreadLocal中的ThreadLocalMap里的Entry实例扩展至弱引用，当ThreadLocal的引用被断开时，
 * 无法回收ThreadLocalMap中的Entry的value，因为此时Entry被当前线程强引用着，由此发生内存泄露。
 * 如何解决：
 * 在ThreadLocal使用完毕后，调用ThreadLocal.remove()方法，将ThreadLocal所指向的Entry的value，一起从内存中清除，调用的方
 * 法是expungeStaleEntry()，将指向Entry的value断开；
 * 备注：虽然ThreadLocal的get(),set()方法也会调用expungeStaleEntry()方法，但是不及时，导致发生内存泄漏
 */
public class ThreadLocalOOM {

    private static final int TASK_LOOP_SIZE = 5;

    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1,
            TimeUnit.MINUTES, new LinkedBlockingQueue<>());

    static class LocalVariable {
        private byte[] bytes = new byte[1025 * 1024 * 5];//申请5M内存
    }

    final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < TASK_LOOP_SIZE; i++) {
            int finalI = i;
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
//                    new LocalVariable();
                    localVariable.set(new LocalVariable());//不调用remove()，会造成内存泄漏
                    localVariable.remove();
                    System.out.println("use local variable " + finalI);
                }
            });
        }
        System.out.println("pool execute over");
    }
}
