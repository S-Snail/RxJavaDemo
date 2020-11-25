package com.snail.rxjavademo.rxjava.obeservable.mapop;

import com.snail.rxjavademo.rxjava.observer.Observer;

/**
 * 适配器类，只留一个onNext方法交给子类实现，其他三个不用写，避免代码污染
 * @param <T>
 * @param <U>
 */
public abstract class BasicFuseableObserver<T,U> implements Observer<T> {

    protected final Observer<U> actual;

    public BasicFuseableObserver(Observer<U> actual) {
        this.actual = actual;
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
