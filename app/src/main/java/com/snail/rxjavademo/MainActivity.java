package com.snail.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.snail.rxjavademo.rxjava.obeservable.Observable;
import com.snail.rxjavademo.rxjava.obeservable.ObservableOnSubscribe;
import com.snail.rxjavademo.rxjava.obeservable.emitter.Emitter;
import com.snail.rxjavademo.rxjava.obeservable.mapop.Function;
import com.snail.rxjavademo.rxjava.observer.Observer;
import com.snail.xx_annotion.EnjoyRetrofit.EnjoyRetrofitActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enjoy_retrofit);
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(Emitter<String> emitter) {
//                emitter.onNext("第一步操作--->");
//                log("subscribe订阅线程 = " + Thread.currentThread().getName());
//            }
//        }).map(new Function<String, String>() {
//            @Override
//            public String apply(String t) {
//                String u = t + "第二步操作--->";
//                log("map订阅线程 = " + Thread.currentThread().getName());
//                return u;
//            }
//        }).map(new Function<String, String>() {
//            @Override
//            public String apply(String t) {
//                String u = t + "第三步操作";
//                return u;
//            }
//        })
//                .subscribeOn()
//                .observeOn()
//                .subscribeObserver(new Observer() {
//                    @Override
//                    public void onNext(Object o) {
//                        log(o.toString());
//                        log("当前线程的名字 = " + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onSubscribe() {
//                        log("onSubscribe()");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        log("onError()");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        log("onComplete()");
//                    }
//                });

        Intent intent = new Intent(this, EnjoyRetrofitActivity.class);
        startActivity(intent);
    }

    public void log(String msg) {
        Log.d("观察者模式", msg);
    }
}