package com.snail.thread.ch01;

/**
 * 新启线程的方式
 */
public class NewThread {
    /*
    扩展自Thread类
     */
    //TODO
    public static class UserThread extends Thread{
        @Override
        public void run() {
            super.run();
            //do my work
            System.out.println("I'm extend thread");
        }
    }

    /*
    实现Runnable接口
     */
    //TODO
    private static class UserRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("I'm implement Runnable");
        }
    }

    public static void main(String[] args) {
        UserThread userThread = new UserThread();
        userThread.start();
        UserRunnable userRunnable = new UserRunnable();
        Thread thread = new Thread(userRunnable);
        thread.start();
    }
}
