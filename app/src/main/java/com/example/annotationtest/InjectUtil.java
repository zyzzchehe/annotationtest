package com.example.annotationtest;

import android.app.Activity;
import java.lang.reflect.Field;

public class InjectUtil {

    public static void inject(Activity activity) {
        Class cl = activity.getClass();
        // 反射
        for (Field field : cl.getDeclaredFields()) {
            // 获取注解
            AnnoTest annotation = field.getAnnotation(AnnoTest.class);
            if(annotation != null){
                String val = annotation.value();
                try {
                    // 插入值
                    field.setAccessible(true);
                    field.set(activity, val);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
