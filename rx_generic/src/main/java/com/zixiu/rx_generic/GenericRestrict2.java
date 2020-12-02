package com.zixiu.rx_generic;

/**
 * Author: Snail
 * Time:  2020/11/30 2:47 PM
 * FileName:  GenericRestrict2
 * 简介：
 */
class GenericRestrict2 {

    /**
     * 不能捕获泛型类型限定的异常
     */
    public <T extends Exception> void catchException(T t) {
//        try{
//
//        }catch (T t1){
//
//        }
    }

    /**
     * 泛型类不能继承Exception或者Throwable
     */
//    public class MyGenericException<T> extends Exception{
//
//    }

//    public class MyGenericThrowable<T> extends Throwable{
//
//    }

    //可以将泛型限定的异常抛出
    public <T extends Exception> void getException(T t) throws T {
        try {

        } catch (Exception e) {
            throw e;
        }
    }

    private class MyException extends Exception {

    }

}
