package com.snail.thread.ch01.threadlocal;

/**
 * 演示错误使用ThreadLocal导致线程不安全
 * 原因：Number为static，多线程共享内存
 *
 * <p>面试题：什么情况下ThreadLocal会线程不安全：</p>
 * <p>答案：多线程下，被static修饰的变量因为是共享内存，导致线程不安全</p>
 */
public class ThreadLocalUnSafe implements Runnable {

    private  Number number = new Number();

    private static ThreadLocal<Number> value = new ThreadLocal<>();

    @Override
    public void run() {
        try {
            //每个线程+1
            number.setNumber(number.getNumber() + 1);
            //存储到ThreadLocal中
            value.set(number);
            Thread.sleep(2);
            System.out.println(Thread.currentThread().getName() + " number = " + value.get().getNumber());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            ThreadLocalUnSafe unSafe = new ThreadLocalUnSafe();
            new Thread(unSafe).start();
        }
    }

    public static class Number {
        private int number;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "number=" + number +
                    '}';
        }
    }
}
