package com.snail.thread.ch01;

import androidx.annotation.NonNull;

/**
 * 在捕捉到中断异常后，中断异常类会将isInterrupted的中断标识位改为false
 */
public class HasInterruptedEx {

    public static class UserThread extends Thread{

        public UserThread(@NonNull String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            while (!isInterrupted()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " 异常 --> " + isInterrupted());
                    //在此正常释放资源
//                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 继承自Thread --> " + isInterrupted());
            }
            System.out.println(Thread.currentThread().getName() + "end -> " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserThread hasInterruptedEx = new UserThread("HasInterruptedEx");
        hasInterruptedEx.start();
        Thread.sleep(500);
        hasInterruptedEx.interrupt();
    }
}
