package com.snail.thread.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 类说明：连接池的实现
 */
public class DBPool {

    public static final LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.add(SqlConnectImpl.fetchConnection());
            }
        }
    }

    /**
     * 释放连接
     */
    public static void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);//把释放的连接，放回连接池
                pool.notifyAll();//通知等待连接线程，重新竞争获取连接
            }
        }
    }

    /**
     * 获取连接
     *
     * @param mills {等待超时时间}
     */
    public static Connection fetchConnection(long mills) throws InterruptedException {
        if (mills < 0) {//无等待上限
            while (pool.isEmpty()) {
                pool.wait();
            }
            return pool.removeFirst();
        } else {
            synchronized (pool) {
                long future = System.currentTimeMillis() + mills;//等待超时的时间
                long remain = mills;
                while (pool.isEmpty() && remain > 0) {
                    pool.wait(remain);
                    remain = future - System.currentTimeMillis();
                }
                Connection connection = null;
                if (!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }

}

