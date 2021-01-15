package com.snail.thread.ch01;

public class EndRunnable {

    public static class UserRunnable implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " I'm implement Runnable");
            }
            System.out.println(Thread.currentThread().getName() + "  interrupt flag = " +
                    Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserRunnable userRunnable = new UserRunnable();
        Thread thread = new Thread(userRunnable);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
