package com.snail.xx_annotion.EnjoyRetrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.http.Url;

public class EnjoyRetrofit implements InvocationHandler {

    public HttpUrl baseUrl;
    public Call.Factory callFactory;
    private Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();

    public EnjoyRetrofit(HttpUrl baseUrl, Call.Factory callFactory) {
        this.baseUrl = baseUrl;
        this.callFactory = callFactory;
    }

    public <T> T create(Class<T> apiClass) {
        return (T) Proxy.newProxyInstance(apiClass.getClassLoader(), new Class[]{apiClass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //解析method上的所有注解信息
        ServiceMethod serviceMethod = loadServiceMethod(method);

        return serviceMethod.invoke(args);
    }

    public static class Builder {

        private HttpUrl baseUrl;
        private Call.Factory callFactory;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = HttpUrl.get(URI.create(baseUrl));
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

    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod serviceMethod = serviceMethodCache.get(method);
        if (serviceMethod != null) return serviceMethod;
        synchronized (serviceMethodCache){
            serviceMethod = serviceMethodCache.get(method);
            if (serviceMethod == null){
                serviceMethod = new ServiceMethod.Builder(this,method).build();
                serviceMethodCache.put(method,serviceMethod);
            }
        }
        return serviceMethod;
    }
}
