package com.zixiu.reflection;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 应用启动状态跟踪
 * 一般写在Application里面的attachBaseContext()方法里，因为这个方法时机最早
 */
public class HookHelper {
    /**
     * Activity的启动监控
     */
    public static void hookHandler(Context context) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        //反射获取ActivityThread的Class对象
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        //获取currentActivityThread的私有方法
        Method currentThreadMethod = activityThreadClass.getDeclaredMethod("currentThreadActivity");
        currentThreadMethod.setAccessible(true);
        //执行currentThreadMethod获取主线程对象
        Object activityThread = currentThreadMethod.invoke(null);
        //获取mH字段
        Field mH = activityThreadClass.getDeclaredField("mH");
        mH.setAccessible(true);
        //获取mH私有字段的值
        Handler handler = (Handler) mH.get(activityThread);
        //反射获取Handler中原始的mCallBack字段
        Field mCallBack = Handler.class.getDeclaredField("mCallBack");
        mCallBack.setAccessible(true);
        //这里设置了我们自己实现了接口的CallBack对象
        mCallBack.set(handler,new CustomerHandler(handler));
    }

    /**
     *用于初始化异步通信Handler，可以截获发送的一系列事件
     */
    public static class CustomerHandler implements Handler.Callback{
        private Handler handler;

        public CustomerHandler(Handler handler) {
            this.handler = handler;
        }

        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.d("TAG", "CustomHandler-handleMessage-" + msg.what);
            //todo 这里每次启动的时候，可以做一些额外的事情
            handler.handleMessage(msg);
            return false;
        }
    }

    /**
     * Activity的创建监控
     * 创建自定义的Instrumentation,然后反射替换，同时重写newActivity方法
     * @throws Exception
     */
    public static void hookInstrumentation() throws Exception{
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityMethod.setAccessible(true);
        //获取主线程对象
        Object activityThreadObject = currentActivityMethod.invoke(null);
        //获取Instrumentation字段
        Field mInstrumentation = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentation.setAccessible(true);
        //获取字段的值
        Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activityThreadObject);
        //偷梁换柱，把系统的Instrumentation换成自己的Instrumentation对象
        CustomInstrumentation customInstrumentation = new CustomInstrumentation(instrumentation);
        mInstrumentation.set(activityThreadObject,customInstrumentation);
    }

    public static class CustomInstrumentation extends Instrumentation{
        private Instrumentation base;

        public CustomInstrumentation(Instrumentation base) {
            this.base = base;
        }

        @Override
        public Activity newActivity(ClassLoader cl, String className, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            Log.d("TAG", "CustomInstrumentation-newActivity-className=" + className);
            Log.d("TAG", "CustomInstrumentation-newActivity-intent=" + intent);
            Log.d("TAG", "CustomInstrumentation-newActivity-ClassLoader=" + cl);
            return super.newActivity(cl, className, intent);
        }
    }

}
