package com.snail.rxjavademo.rxjava.obeservable.mapop;

import com.snail.rxjavademo.rxjava.obeservable.Observable;
import com.snail.rxjavademo.rxjava.obeservable.ObservableSource;

/**
 * 装饰类
 */
public abstract class AbstractObservableWithUpStream<T,U> extends Observable<U> {

    protected final ObservableSource<T> source;

    public AbstractObservableWithUpStream(ObservableSource<T> source) {
        this.source = source;
    }
}
