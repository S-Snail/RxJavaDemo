package com.snail.rxjavademo.rxjava.obeservable.thread;

import com.snail.rxjavademo.rxjava.obeservable.ObservableSource;
import com.snail.rxjavademo.rxjava.obeservable.mapop.AbstractObservableWithUpStream;
import com.snail.rxjavademo.rxjava.observer.Observer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 订阅过程切换线程用的
 */
public class ObservableSubscribeOn<T> extends AbstractObservableWithUpStream<T, T> {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public ObservableSubscribeOn(ObservableSource<T> source) {
        super(source);
    }

    @Override
    protected void subscribeActual(Observer observer) {
        final SubscribeOnObserver<T> parent = new SubscribeOnObserver<>(observer);
        observer.onSubscribe();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                source.subscribeObserver(parent);
            }
        });
    }

    static final class SubscribeOnObserver<T> implements Observer<T>{

        private Observer<T> actual;

        public SubscribeOnObserver(Observer<T> actual) {
            this.actual = actual;
        }

        @Override
        public void onNext(T t) {

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
}
