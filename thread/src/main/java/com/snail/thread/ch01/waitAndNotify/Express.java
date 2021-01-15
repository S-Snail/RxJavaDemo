package com.snail.thread.ch01.waitAndNotify;

/**
 * 多线程协作，锁住方法synchronized，验证wait(),notify()与notifyAll()
 * 解析：
 *  wait()释放锁，notify()与notifyAll()代码执行完才释放锁
 *  notify()随机唤醒一个等待线程，notifyAll()唤醒全部等待线程，等待线程重新竞争锁，获取执行权
 */
public class Express {
    public final static String City = "ShangHai";//出发地
    private String site;//地点变化
    private int km;//里程数变化

    public Express() {
    }

    public Express(String site, int km) {
        this.site = site;
        this.km = km;
    }

    public synchronized void changeSite(String site){
        this.site = site;
//        notifyAll();
        notify();
    }

    public synchronized void changeKm(int km){
        this.km = km;
        notifyAll();
    }

    public synchronized void waitKm() throws InterruptedException {
        while (km <= 1000){
            wait();
            System.out.println(Thread.currentThread().getId() + " 等待大于1000。。。");
        }
        System.out.println(Thread.currentThread().getId() + " 大于1000。");
    }

    public synchronized void waitSite() throws InterruptedException {
        while(!site.equals("Beijing")){
            wait();
            System.out.println(Thread.currentThread().getId() + " 等待到达北京。");
        }
        System.out.println(Thread.currentThread().getId() + " 已到达北京。");
    }
}
