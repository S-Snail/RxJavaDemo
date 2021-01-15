package com.snail.thread.ch01;

import androidx.annotation.NonNull;

/**
 * interrupt()               发起中断，线程不一定中断
 * isInterrupted()           中断标识为置true
 * static interrupt()        中断标识为置false（不将中断标识位置true）
 * 面试题：与stop()的区别，
 * 答：stop()方法强制中断线程，导致线程所占资源不能正常释放
 */
public class EndThread {

    private static class UserThread extends Thread {

        public UserThread(@NonNull String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " interrupt flag = " + isInterrupted());
//            while (!isInterrupted()){
            while (Thread.interrupted()) {
//            while (true){
                System.out.println(threadName + " is running");
                System.out.println(threadName + " inner interrupt flag = " + isInterrupted());
            }
            System.out.println(threadName + " inner interrupt flag = " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserThread endThread = new UserThread("endThread");
        endThread.start();
        Thread.sleep(1000);
        endThread.interrupt();
    }
}
