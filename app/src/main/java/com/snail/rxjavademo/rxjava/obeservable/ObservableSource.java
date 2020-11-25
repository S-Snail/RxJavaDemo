package com.snail.rxjavademo.rxjava.obeservable;

import com.snail.rxjavademo.rxjava.observer.Observer;

/**
 * 绑定Observable与Observer
 * 订阅过程，添加观察者
 */
public interface ObservableSource<T> {
    //添加观察者
    void subscribeObserver(Observer<T> observer);
}
