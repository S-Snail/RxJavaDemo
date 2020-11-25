package com.snail.rxjavademo.rxjava.obeservable.mapop;

import com.snail.rxjavademo.rxjava.obeservable.ObservableSource;
import com.snail.rxjavademo.rxjava.observer.Observer;

public class ObservableMap<T, U> extends AbstractObservableWithUpStream<T, U> {

    final Function<T, U> function;

    public ObservableMap(ObservableSource<T> source, Function<T, U> function) {
        super(source);
        this.function = function;
    }

    /**
     * 真实的功能
     *
     * @param observer
     */
    @Override
    protected void subscribeActual(Observer observer) {
        source.subscribeObserver(new MapObserver<>(observer, function));
    }

    static final class MapObserver<T, U> extends BasicFuseableObserver<T, U> {

        final Function<T, U> mapper;

        public MapObserver(Observer<U> actual, Function<T, U> mapper) {
            super(actual);
            this.mapper = mapper;
        }

        @Override
        public void onNext(T t) {
            U apply = mapper.apply(t);
            actual.onNext(apply);
        }
    }
}
