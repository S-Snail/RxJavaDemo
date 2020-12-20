package com.snail.xx_annotion.inject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class InjectViewUtil {

    //通过注解+反射实现findViewById
    public static void inject(Activity activity) throws IllegalAccessException {
        Class<? extends Activity> cls = activity.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectView.class)){
                InjectView annotation = field.getAnnotation(InjectView.class);
                int id = annotation.value();
                View view = activity.findViewById(id);
                field.setAccessible(true);
                field.set(activity,view);
            }
        }
    }
}
