package com.snail.thread.ch01;

class TestSyncMain {

    public static class ThreadA extends Thread{
        TestSync testSync;

        public ThreadA(TestSync testSync) {
            this.testSync = testSync;
        }

        @Override
        public void run() {
            super.run();
            testSync.methodA();//对象锁
//            TestSync.methodA();//（类锁）static 修饰的是单实例的，Class锁可以对类对所有对象实例起作用
        }
    }

    public static class ThreadB extends Thread{
        TestSync testSync;

        public ThreadB(TestSync testSync) {
            this.testSync = testSync;
        }

        @Override
        public void run() {
            super.run();
            testSync.methodB();
//            TestSync.methodB();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestSync testSync = new TestSync();
        ThreadA threadA = new ThreadA(testSync);
        threadA.start();

        ThreadB threadB = new ThreadB(testSync);
        threadB.start();
    }
}
