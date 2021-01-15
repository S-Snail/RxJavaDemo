package com.snail.thread.ch02.forkjoin;

import java.util.Random;

public class MakeArray {

    //定义一个长度为4000的数组
    public static int ARRAY_LENGTH = 4000;

    public static int[] makeArray() {
        int[] result = new int[ARRAY_LENGTH];
        Random random = new Random();
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            result[i] = random.nextInt(ARRAY_LENGTH * 3);
        }
        return result;
    }

}
