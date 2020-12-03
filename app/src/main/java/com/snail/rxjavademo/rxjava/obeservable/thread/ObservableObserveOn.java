package com.snail.rxjavademo.rxjava.obeservable.thread;

import android.os.Handler;
import android.os.Looper;

import com.snail.rxjavademo.rxjava.obeservable.ObservableSource;
import com.snail.rxjavademo.rxjava.obeservable.mapop.AbstractObservableWithUpStream;
import com.snail.rxjavademo.rxjava.observer.Observer;

public class ObservableObserveOn<T> extends AbstractObservableWithUpStream {

    public ObservableObserveOn(ObservableSource source) {
        super(source);
    }

    @Override
    protected void subscribeActual(Observer observer) {
        ObserveOnObserver<T> parent = new ObserveOnObserver<>(observer);
        source.subscribeObserver(parent);
    }

    static final class ObserveOnObserver<T> implements Observer<T> {
        private Observer<T> actual;

        private Handler handler;

        public ObserveOnObserver(Observer<T> actual) {
            this.actual = actual;
            handler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void onNext(final T t) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    actual.onNext(t);
                }
            });
        }

        @Override
        public void onSubscribe() {
            actual.onSubscribe();
        }

        @Override
        public void onError(Throwable e) {
            actual.onError(e);
        }

        @Override
        public void onComplete() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    actual.onComplete();
                }
            });
        }
    }
}
