package com.snail.rxjavademo.rxjava.obeservable;

import com.snail.rxjavademo.rxjava.obeservable.mapop.Function;
import com.snail.rxjavademo.rxjava.obeservable.mapop.ObservableMap;
import com.snail.rxjavademo.rxjava.obeservable.thread.ObservableObserveOn;
import com.snail.rxjavademo.rxjava.obeservable.thread.ObservableSubscribeOn;
import com.snail.rxjavademo.rxjava.observer.Observer;

/**
 * 具体的被观察者
 */
public abstract class Observable<T> implements ObservableSource {
    @Override
    public void subscribeObserver(Observer observer) {
//        observer.onNext("message");//预留给程序员,让这个类的子类去做发消息的操作,map,flatMap,contactMap...
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer observer);

    /**
     * 创建具体的观察者，给程序员用
     */
    public static <T> Observable create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate<>(source);
    }

    /**
     * map操作->创建具体的被观察者
     */
    public <U> Observable map(Function<T, U> function) {
        return new ObservableMap(this, function);
    }


    /**
     * 创建Observer的线程切换
     */
    public final Observable<T> observeOn() {
        return new ObservableObserveOn<T>(this);
    }

    /**
     * 创建Observable的线程切换
     */

    public final Observable<T> subscribeOn(){
        return new ObservableSubscribeOn<T>(this);
    }

}
