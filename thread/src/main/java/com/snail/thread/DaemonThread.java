package com.snail.thread;

/**
 * 守护线程
 */
public class DaemonThread {

    public static class UserThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                while (!isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " I'm extends Thread");
                }
                System.out.println(Thread.currentThread().getName() + " interrupt flag is = " +
                        isInterrupted());
            } finally {
                //守护线程中 finally不一定起作用
                System.out.println("-----------finally");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserThread userThread = new UserThread();
        //设置线程的优先级
//        userThread.setPriority(1);//1~10,默认为5
        userThread.setDaemon(true);//守护线程：当没有用户线程时，守护线程就自动结束了
        userThread.start();
        Thread.sleep(1000);
//        userThread.interrupt();
    }

}
