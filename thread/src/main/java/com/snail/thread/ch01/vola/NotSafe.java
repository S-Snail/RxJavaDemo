package com.snail.thread.ch01.vola;

/**
 * volatile与synchronized辨析
 * volatile:是最轻量的同步机制，但是不能保证数据的原子性，适用于"一写多读"的场景
 * synchronized:同步锁机制，锁的是对象，能保证数据的原子性
 */
public class NotSafe {
    private static volatile int count = 0;

    private void incCount(){
        count++;
    }

    private static class Count extends Thread{
        private final NotSafe notSafe;

        public Count(NotSafe notSafe) {
            this.notSafe = notSafe;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10000; i++) {
                notSafe.incCount();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NotSafe notSafe = new NotSafe();
        Count count1 = new Count(notSafe);
        Count count2 = new Count(notSafe);
        count1.start();
        count2.start();
        Thread.sleep(50);
        System.out.println("当前count = " + notSafe.count);
    }

}
