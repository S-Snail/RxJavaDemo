package com.snail.xx_annotion.homework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class IntentExtraInjectUtil {

    public static Intent injectActIntent(Activity sourceActivity) throws IllegalAccessException {
        Intent intent = new Intent();
        Class<? extends Activity> sourceActClass = sourceActivity.getClass();
        Field[] fields = sourceActClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Autowired annotation = field.getAnnotation(Autowired.class);
                String key = annotation.value();
                //todo 获取字段对应的值
                field.setAccessible(true);
                Object value = field.get(sourceActivity);
                String fieldName = "put" + field.getName() + "()";
                Class<?> componentType = field.getType().getComponentType();
                if (value instanceof String) {
                    intent.putExtra(key, (String) value);
                } else if (value instanceof Boolean) {
                    intent.putExtra(key, (Boolean) value);
                }
            }
        }
        return intent;
    }

    //赋值注入
    public static void injectValue(Activity targetActivity) {
        Intent intent = targetActivity.getIntent();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        Class<? extends Activity> targetActClass = targetActivity.getClass();
        Field[] fields = targetActClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Autowired annotation = field.getAnnotation(Autowired.class);
                String key = TextUtils.isEmpty(annotation.value()) ? field.getName() : annotation.value();
                if (extras.containsKey(key)) {
                    Object obj = extras.get(key);
                    //Parcelable数组类型不能直接设置，其他的都可以
                    //获得数组单个元素类型
                    Class<?> componentType = field.getType().getComponentType();
                    //当前属性是数组，并且是Parcelable（子类）数组
                    if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objs = (Object[]) obj;
                        //创建对应类型的数组并由objs拷贝
                        Arrays.copyOf(objs, objs.length, (Class<? extends java.lang.Object[]>) field.getType());
                        obj = objs;
                    }
                    field.setAccessible(true);
                    try {
                        field.set(targetActivity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
