package com.snail.thread.ch02.forkjoin;

/**
 * 测试单线程累加
 */
public class SumNormal {
    public static void main(String[] args) {
        int count = 0;
        int[] array = MakeArray.makeArray();
        System.out.println("单线程测试累加开始。。。");
        long start = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            count = count + array[i];
        }
        System.out.println("单线程测试累加所需时间：" + (System.currentTimeMillis() - start));
    }
}
