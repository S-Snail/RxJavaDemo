package com.snail.rxjavademo.rxjava.obeservable.mapop;

/**
 * 事件变换
 */
public interface Function<T, R> {
    R apply(T t);
}
