package com.snail.thread.ch02.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 分而治之计算数组的和
 */
public class SumArray {

    private static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;

    public static class SumTask extends RecursiveTask<Integer> {

        private int[] src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int start, int end) {
            this.src = src;
            this.fromIndex = start;
            this.toIndex = end;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex <= THRESHOLD) {
                System.out.println("最小任务：fromIndex = " + fromIndex + "\ttoIndex = " + toIndex);
                int count = 0;
                for (int i = 0; i < src.length; i++) {
                    count = count + src[i];
                }
                return count;
            } else {
                int middle = (toIndex + fromIndex) / 2;
                SumTask leftTask = new SumTask(src, fromIndex, middle);
                SumTask rightTask = new SumTask(src, middle + 1, toIndex);
                invokeAll(leftTask, rightTask);
                return leftTask.join() + rightTask.join();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] array = MakeArray.makeArray();
        SumTask sumTask = new SumTask(array, 0, array.length - 1);
        long start = System.currentTimeMillis();
        pool.invoke(sumTask);//同步方法
        System.out.println("ForkJoin统计数组的和所花费的时间：" + (System.currentTimeMillis() - start) +
                "\t最终的结果：" + sumTask.join());
    }

}
