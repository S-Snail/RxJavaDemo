package com.snail.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.snail.rxjavademo.rxjava.obeservable.Observable;
import com.snail.rxjavademo.rxjava.obeservable.ObservableOnSubscribe;
import com.snail.rxjavademo.rxjava.obeservable.emitter.Emitter;
import com.snail.rxjavademo.rxjava.obeservable.mapop.Function;
import com.snail.rxjavademo.rxjava.observer.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("第一步操作--->");
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String t) {
                String u = t + "第二步操作--->";
                return u;
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String t) {
                String u = t + "第三步操作";
                return u;
            }
        })
                .subscribeObserver(new Observer() {
                    @Override
                    public void onNext(Object o) {
                        log(o.toString());
                    }

                    @Override
                    public void onSubscribe() {
                        log("onSubscribe()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        log("onError()");
                    }

                    @Override
                    public void onComplete() {
                        log("onComplete()");
                    }
                });
    }

    public void log(String msg) {
        Log.d("观察者模式", msg);
    }
}