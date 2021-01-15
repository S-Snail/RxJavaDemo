package com.snail.thread.ch01.threadlocal;

public class NoThreadLocal {
    static Integer count = new Integer(1);
    private void runThreeThread(){
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(new TestTask(i));
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    public static class TestTask implements Runnable{

        private int id;

        public TestTask(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : start");
            count = count + id;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public static void main(String[] args) {
        NoThreadLocal noThreadLocal = new NoThreadLocal();
        noThreadLocal.runThreeThread();
    }
}
