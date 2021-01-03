package com.snail.cusretrofit.cus_retrofit;

import com.snail.cusretrofit.annotation.Field;
import com.snail.cusretrofit.annotation.GET;
import com.snail.cusretrofit.annotation.POST;
import com.snail.cusretrofit.annotation.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class ServiceMethod {

    private final HttpUrl baseUrl;
    private final String httpMethod;
    private final String relativeUrl;
    private final boolean hasBody;
    private final ParameterHandler[] parameterHandlers;
    private final Call.Factory callFactory;
    private FormBody.Builder formBuilder;
    private HttpUrl.Builder urlBuilder;

    public ServiceMethod(Builder builder) {
        this.baseUrl = builder.cusRetrofit.baseUrl;
        this.callFactory = builder.cusRetrofit.callFactory;
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.hasBody = builder.hasBody;
        this.parameterHandlers = builder.parameterHandlers;
        if (hasBody) {
            formBuilder = new FormBody.Builder();
        }
    }

    public void addFieldParameter(String key, String value) {
        formBuilder.add(key, value);
    }

    public void addQueryParameter(String key, String value) {
        if (urlBuilder == null) {
            urlBuilder = baseUrl.newBuilder(relativeUrl);
        }
        urlBuilder.addQueryParameter(key, value);
    }

    public Object invoke(Object[] args) {
        for (int i = 0; i < parameterHandlers.length; i++) {
            parameterHandlers[i].apply(this, args[i].toString());
        }
        HttpUrl url;
        if (urlBuilder == null) {
            urlBuilder = baseUrl.newBuilder(relativeUrl);
        }
        url = urlBuilder.build();

        FormBody formBody = null;
        if (formBuilder != null) {
            formBody = formBuilder.build();
        }

        Request request = new Request.Builder().url(url).method(httpMethod, formBody).build();
        return callFactory.newCall(request);
    }

    public static class Builder {
        private final CusRetrofit cusRetrofit;
        private final Annotation[] methodAnnotations;
        private final Annotation[][] parameterAnnotations;
        private String relativeUrl;
        private boolean hasBody;
        private String httpMethod;
        private ParameterHandler[] parameterHandlers;

        public Builder(CusRetrofit cusRetrofit, Method method) {
            this.cusRetrofit = cusRetrofit;
            methodAnnotations = method.getAnnotations();
            parameterAnnotations = method.getParameterAnnotations();
        }

        public ServiceMethod build() {
            //解析方法上的注解
            for (Annotation methodAnnotation : methodAnnotations) {
                if (methodAnnotation instanceof POST) {
                    relativeUrl = ((POST) methodAnnotation).value();
                    httpMethod = "POST";
                    hasBody = true;
                } else if (methodAnnotation instanceof GET) {
                    relativeUrl = ((GET) methodAnnotation).value();
                    httpMethod = "GET";
                    hasBody = false;
                }
            }

            //解析参数的注解
            int length = parameterAnnotations.length;
            parameterHandlers = new ParameterHandler[length];
            for (int i = 0; i < length; i++) {
                //获取每一个参数上的注解
                Annotation[] parameterAnnotation = parameterAnnotations[i];
                for (Annotation annotation : parameterAnnotation) {
                    if (annotation instanceof Field) {
                        String value = ((Field) annotation).value();
                        //保存参数注解key(此处设计很巧妙)
                        parameterHandlers[i] = new ParameterHandler.FiledParameterHandler(value);
                    } else if (annotation instanceof Query) {
                        String value = ((Query) annotation).value();
                        parameterHandlers[i] = new ParameterHandler.QueryParameterHandler(value);
                    }
                }
            }
            return new ServiceMethod(this);
        }
    }
}
