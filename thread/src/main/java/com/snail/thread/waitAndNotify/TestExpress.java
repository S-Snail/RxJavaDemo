package com.snail.thread.waitAndNotify;

/**
 * 多线程协作测试类
 */
public class TestExpress {

    private static Express express = new Express(Express.City,0);

    /**
     * 测试城市变化线程
     */
    public static class TestSiteChange extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                express.waitSite();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试里程数变化线程
     */
    public static class TestKmChange extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                express.waitKm();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new TestSiteChange().start();
        }

        for (int i = 0; i < 3; i++) {
            new TestKmChange().start();
        }
        Thread.sleep(3000);//等待三秒后，开始发货
//        express.changeKm(0);
//        express.changeSite(Express.City);
//        Thread.sleep(2000);//再等2秒，快递到达
//        express.changeKm(1001);
        express.changeSite("Beijing");
    }



}
