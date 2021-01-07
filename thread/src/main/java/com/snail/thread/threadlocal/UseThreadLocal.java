package com.snail.thread.threadlocal;

import androidx.annotation.Nullable;

/**
 * ThreadLocal是使线程保留定义在ThreadLocal里面变量的副本，线程中持有ThreadLocal.ThreadLocalMap，
 * 其中是以ThreadLocal为key，传入变量的值为value进行数据保存，由此做到的线程隔离
 * 在ThreadLocal.get()中通过threadLocal为key，经过一系列复杂的位运算，获取当前定义在ThreadLocal中的变量的值
 */
public class UseThreadLocal {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Nullable
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };


    /**
     * 类说明：线程的任务是将ThreadLocal的值变化并写回，观察线程之间是否会相互影响
     */
    public static class TestThreadLocal extends Thread {

        private final int id;

        public TestThreadLocal(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            super.run();
            System.out.println(Thread.currentThread().getName() + " : is start");
            Integer s = threadLocal.get();
            s = s + id;
            threadLocal.set(s);
            System.out.println(Thread.currentThread().getName() + " : value = " + threadLocal.get());
        }
    }

    private static void startThreeThread() {
        TestThreadLocal[] testThreadLocal = new TestThreadLocal[3];
        for (int i = 0; i < 3; i++) {
            testThreadLocal[i] = new TestThreadLocal(i);
        }

        for (int i = 0; i < testThreadLocal.length; i++) {
            TestThreadLocal threadLocal = testThreadLocal[i];
            threadLocal.start();
            System.out.println(threadLocal.getName() + " hashCode = " + threadLocal.hashCode());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(50);
        startThreeThread();
    }

}
