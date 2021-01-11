package com.snail.thread.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class DBPoolTest {
    static DBPool pool = new DBPool(10);
    //控制main线程将会等待所有的Worker执行结束后才会继续执行
    private static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        //线程数量
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        int count = 20;//每个线程的操作次数
        AtomicInteger got = new AtomicInteger();//计数器：统计可以拿到连接的线程
        AtomicInteger ungot = new AtomicInteger();//计数器：统计没有拿到连接的线程
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count, got, ungot), "worker_" + i);
            thread.start();
        }
        end.await();//main线程在此处等待
        System.out.println("总共尝试" + (count * threadCount) + "次");
        System.out.println("拿到连接的次数：" + got);
        System.out.println("没拿到连接的次数：" + ungot);
    }

    public static class Worker implements Runnable {

        private int count;
        private AtomicInteger got;
        private AtomicInteger ungot;

        public Worker(int count, AtomicInteger got, AtomicInteger ungot) {
            this.count = count;
            this.got = got;
            this.ungot = ungot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    Connection connection = DBPool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        ungot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + " 等待超时！");
                    }
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
