package com.snail.cusretrofit.cus_retrofit;

import android.net.Uri;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class CusRetrofit implements InvocationHandler {

    public final HttpUrl baseUrl;
    public final Call.Factory callFactory;
    private Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();

    public CusRetrofit(HttpUrl baseUrl, Call.Factory callFactory) {
        this.baseUrl = baseUrl;
        this.callFactory = callFactory;
    }

    public <T> T create(Class<T> apiClass) {
        return (T) Proxy.newProxyInstance(apiClass.getClassLoader(), new Class[]{apiClass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //解析方法上的注解（包括方法注解和参数注解）
        ServiceMethod serviceMethod = loadService(method);
        return serviceMethod.invoke(args);
    }

    private ServiceMethod loadService(Method method) {
        ServiceMethod result = serviceMethodCache.get(method);
        if (result != null) return result;
        synchronized (serviceMethodCache) {
            result = serviceMethodCache.get(method);
            if (result == null) {
                result = new ServiceMethod.Builder(this, method).build();
                serviceMethodCache.put(method, result);
            }
        }
        return result;
    }

    public static class Builder {

        private HttpUrl baseUrl;
        private Call.Factory callFactory;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = HttpUrl.parse(Uri.parse(baseUrl).toString());
            return this;
        }

        public Builder callFactory(Call.Factory callFactory) {
            this.callFactory = callFactory;
            return this;
        }

        public CusRetrofit build() {
            if (baseUrl == null)
                throw new IllegalStateException("Base Url can't be null");
            if (callFactory == null) {
                callFactory = new OkHttpClient();
            }
            return new CusRetrofit(baseUrl, callFactory);
        }
    }
}
