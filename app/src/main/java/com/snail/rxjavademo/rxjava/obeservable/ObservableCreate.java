package com.snail.rxjavademo.rxjava.obeservable;


import com.snail.rxjavademo.rxjava.obeservable.emitter.Emitter;
import com.snail.rxjavademo.rxjava.observer.Observer;

public class ObservableCreate<T> extends Observable {

    private ObservableOnSubscribe source;

    public ObservableCreate(ObservableOnSubscribe observableOnSubscribe) {
        this.source = observableOnSubscribe;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        observer.onSubscribe();
        source.subscribe(new CreateEmitter(observer));
    }

    /**
     * 发射器的实现类
     */
    static final class CreateEmitter<T> implements Emitter<T>{
        final Observer observer;

        public CreateEmitter(Observer observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            observer.onNext(t);
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
