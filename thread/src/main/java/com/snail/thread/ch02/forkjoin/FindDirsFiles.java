package com.snail.thread.ch02.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 使用forkjoin -> recursiveAction查找指定类型的文件
 */
public class FindDirsFiles extends RecursiveAction {

    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        List<FindDirsFiles> taskList = new ArrayList<>();
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    taskList.add(new FindDirsFiles(file));
                } else if (file.getAbsolutePath().endsWith("txt")) {
                    System.out.println("文件：" + file.getAbsolutePath());
                }
            }
            if (!taskList.isEmpty()) {
                for (FindDirsFiles task : invokeAll(taskList)) {
                    task.join();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //用一个ForkJoinPool实例调度总任务
        ForkJoinPool pool = new ForkJoinPool();
        FindDirsFiles task = new FindDirsFiles(new File("/Users/lizhongcheng/AndroidProject/RxJavaDemo"));
        pool.execute(task);//异步任务

        System.out.println("Task is Running......");
        Thread.sleep(1);
        int otherWork = 0;
        for(int i=0;i<100;i++){
            otherWork = otherWork+i;
        }
        System.out.println("Main Thread done sth......,otherWork="+otherWork);
        task.join();//阻塞方法
        System.out.println("Task end");
    }
}
