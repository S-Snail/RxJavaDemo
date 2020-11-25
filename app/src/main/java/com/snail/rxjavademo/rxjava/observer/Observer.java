package com.snail.rxjavademo.rxjava.observer;

/**
 * 抽象观察者
 *
 * @param <T>
 */
public interface Observer<T> {
    //接收消息
    void onNext(T t);

    //订阅 - 建立关联
    void onSubscribe();

    //异常
    void onError(Throwable e);

    //接收消息完成
    void onComplete();

}
