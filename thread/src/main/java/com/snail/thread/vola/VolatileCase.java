package com.snail.thread.vola;

public class VolatileCase {

    private static int code = 100;
    private static volatile boolean ready;

    private static class UserClass extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("PrintThread is start......");
            while (!ready) ;//开启无限循环
            System.out.println("thread end code = " + code);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new UserClass().start();
        Thread.sleep(1000);
        ready = true;
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + " end");
    }

}
