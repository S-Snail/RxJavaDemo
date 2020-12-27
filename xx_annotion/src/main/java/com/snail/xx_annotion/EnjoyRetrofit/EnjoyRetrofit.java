package com.snail.xx_annotion.EnjoyRetrofit;

import com.snail.xx_annotion.EnjoyRetrofit.api.EnjoyWeatherApi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class EnjoyRetrofit implements InvocationHandler {

    private HttpUrl baseUrl;
    private Call.Factory callFactory;

    public EnjoyRetrofit(HttpUrl baseUrl, Call.Factory callFactory) {
        this.baseUrl = baseUrl;
        this.callFactory = callFactory;
    }

    public <T> T create(Class<T> apiClass) {
        return (T) Proxy.newProxyInstance(apiClass.getClassLoader(),apiClass.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //解析method上的所有注解信息

        return null;
    }

    public static class Builder {

        private HttpUrl baseUrl;
        private Call.Factory callFactory;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = HttpUrl.get(baseUrl);
            return this;
        }

        public Builder callFactory(Call.Factory callFactory) {
            this.callFactory = callFactory;
            return this;
        }

        public EnjoyRetrofit build() {
            if (baseUrl == null) {
                throw new IllegalStateException("BaseUrl required");
            }
            if (callFactory == null) {
                callFactory = new OkHttpClient();
            }
            return new EnjoyRetrofit(baseUrl, callFactory);
        }
    }
}
