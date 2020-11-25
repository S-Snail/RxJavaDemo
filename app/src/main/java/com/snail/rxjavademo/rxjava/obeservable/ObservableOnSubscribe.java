package com.snail.rxjavademo.rxjava.obeservable;

import com.snail.rxjavademo.rxjava.obeservable.emitter.Emitter;

/**
 * 将发射器Emitter和Observable绑定到一起
 */
public interface ObservableOnSubscribe<T> {
    void subscribe(Emitter<T> emitter);
}
