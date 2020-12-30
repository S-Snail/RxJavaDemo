package com.snail.xx_annotion.EnjoyRetrofit;

import com.snail.xx_annotion.EnjoyRetrofit.annotation.Field;
import com.snail.xx_annotion.EnjoyRetrofit.annotation.GET;
import com.snail.xx_annotion.EnjoyRetrofit.annotation.POST;
import com.snail.xx_annotion.EnjoyRetrofit.annotation.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * 专门用来解析方法中的各种注解信息
 */
public class ServiceMethod {


    private final HttpUrl baseUrl;
    private final Call.Factory callFactory;
    private final String httpMethod;
    private final String relativeUrl;
    private final boolean hasBody;
    private final ParameterHandler[] parameterHandlers;
    private FormBody.Builder formBuild;
    //最终完整的url
    HttpUrl.Builder urlBuilder;

    public ServiceMethod(Builder builder) {
        this.baseUrl = builder.enjoyRetrofit.baseUrl;
        callFactory = builder.enjoyRetrofit.callFactory;
        httpMethod = builder.httpMethod;
        relativeUrl = builder.relativeUrl;
        hasBody = builder.hasBody;
        parameterHandlers = builder.parameterHandlers;
        //如果有请求体，则创建一个请求体对象，装请求的数据
        if (hasBody){
            formBuild = new FormBody.Builder();
        }
    }

    public Object invoke(Object[] args) {
        /**
         * 1、处理请求的地址与参数
         */
        //处理请求地址
        for (int i = 0; i < parameterHandlers.length; i++) {
            ParameterHandler handlers = parameterHandlers[i];
            //handlers里本来就记录了key,现在给到对应的value
            handlers.apply(this,args[i].toString());
        }
        //最终请求地址
        HttpUrl url;
        if (urlBuilder == null){//如果不是GET请求，urlBuilder肯定为null
            urlBuilder = baseUrl.newBuilder(relativeUrl);
        }
        url = urlBuilder.build();

        //请求体
        FormBody formBody = null;
        if (formBuild != null){
            formBody = formBuild.build();
        }

        Request request = new Request.Builder().url(url).method(httpMethod, formBody).build();//GET请求时，formBody为null
        return callFactory.newCall(request);
    }

    /**
     * GET请求，把k-v拼到url里面
     * @param key
     * @param value
     */
    public void addQueryParameter(String key, String value) {
        if (urlBuilder == null){
            urlBuilder = baseUrl.newBuilder(relativeUrl);
        }
        urlBuilder.addQueryParameter(key,value);
    }

    /**
     * POST请求，把k-v放到请求体中
     * @param key
     * @param value
     */
    public void addFieldParameter(String key, String value) {
        formBuild.add(key, value);
    }


    public static class Builder {
        private final EnjoyRetrofit enjoyRetrofit;
        private final Annotation[] methodAnnotations;
        private final Annotation[][] parameterAnnotations;
        private String httpMethod;
        private String relativeUrl;
        private boolean hasBody;
        private ParameterHandler[] parameterHandlers;

        public Builder(EnjoyRetrofit enjoyRetrofit, Method method) {
            this.enjoyRetrofit = enjoyRetrofit;
            //获取方法上的所有的注解
            methodAnnotations = method.getAnnotations();
            //获得方法参数的所有的注解
            parameterAnnotations = method.getParameterAnnotations();
        }

        public ServiceMethod build() {
            /**
             * 1、解析方法上的注解，这里只处理POST与GET
             */
            for (Annotation methodAnnotation : methodAnnotations) {
                if (methodAnnotation instanceof POST) {
                    //记录当前请求方式，用于okhttp request method
                    this.httpMethod = "POST";
                    //记录请求URL的path地址
                    this.relativeUrl = ((POST) methodAnnotation).value();
                    //记录是否有请求体
                    this.hasBody = true;
                } else if (methodAnnotation instanceof GET) {
                    this.httpMethod = "GET";
                    this.relativeUrl = ((GET) methodAnnotation).value();
                    this.hasBody = false;
                }
            }

            /**
             * 2 解析方法参数的注解
             */
            int length = parameterAnnotations.length;
            parameterHandlers = new ParameterHandler[length];
            for (int i = 0; i < length; i++) {
                //一个参数上所有的注解
                Annotation[] annotations = parameterAnnotations[i];
                //处理参数上的每一个注解
                for (Annotation annotation : annotations) {
                    //todo 可以加一个判断：如果httpMethod是GET请求，现在又解析到Field注解，可以提示使用者使用Query注解
                    //                    if ("GET".equals(httpMethod) && annotation instanceof Field) {
                    //                        throw new IllegalStateException("please user @Query");
                    //                    } else if ("POST".equals(httpMethod) && annotation instanceof Query) {
                    //                        throw new IllegalStateException("please use @POST");
                    //                    }
                    if (annotation instanceof Field) {
                        //获取请求参数名
                        String value = ((Field) annotation).value();
                        // 保存请求参数
                        parameterHandlers[i] = new ParameterHandler.FieldParameterHandler(value);
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
