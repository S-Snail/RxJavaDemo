package com.snail.thread.ch01;

public class UseJoin {

    public static class Goddess implements Runnable {
        Thread thread;

        public Goddess(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            System.out.println("女神开始排队打饭。。。");
            if (thread != null) {
                try {
                    Thread.sleep(2000);
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("女神打饭完成。");
        }
    }

    public static class GoddessBoyfriend implements Runnable {

        @Override
        public void run() {
            System.out.println("女神男友开始排队打饭。。。");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("女神男友打饭完成。");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("屌丝开始排队打饭。。。");
        GoddessBoyfriend goddessBoyfriend = new GoddessBoyfriend();
        Thread goddessBoyFriendThread = new Thread(goddessBoyfriend);
        Goddess goddess = new Goddess(goddessBoyFriendThread);
        Thread goddessThread = new Thread(goddess);
        Thread.sleep(2000);
        goddessThread.start();
        goddessBoyFriendThread.start();
        goddessThread.join();//插队操作
        System.out.println("屌丝打饭完成");
    }
}
