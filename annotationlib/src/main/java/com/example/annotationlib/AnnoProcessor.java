package com.example.annotationlib;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnoProcessor {

    static final String TAG = AnnoProcessor.class.getSimpleName();

    public static void bind(Activity activity){
        bindView(activity);
        bindClick(activity);
    }


    // 绑定view
    private static void bindView(Activity activity){
        Class cls = activity.getClass();
        // 获取私有属性
        Field[] fields = cls.getDeclaredFields();
        for (Field field: fields) {
            Log.d("zyzzchehe","field name = "+field.getName());
            MyAnno myAnno = field.getAnnotation(MyAnno.class);
            if(myAnno != null){
                int id = myAnno.value();
                field.setAccessible(true);
                try {
                    field.set(activity,activity.findViewById(id));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //绑定事件
    private static void bindClick(final Activity activity){
        Class cls = activity.getClass();
        Method[] methods = cls.getDeclaredMethods();
        for(final Method method : methods){

            OnClick click = method.getAnnotation(OnClick.class);
            OnLongClick longClick = method.getAnnotation(OnLongClick.class);

            //点击事件
            if (click != null && click.value() != 0) {
                View view = activity.findViewById(click.value());//通过注解的值获取View控件
                if (view == null) return;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            method.invoke(activity, v);//通过反射来调用被注解修饰的方法，把View传回去，需要一个参数View view
                        } catch (InvocationTargetException e) {
                            Log.e(TAG, "InvocationTargetException = " + e.toString());
                        } catch (IllegalAccessException e) {
                            Log.e(TAG, "IllegalAccessException = " + e.toString());
                        }
                    }
                });
            }

            //长按事件
            if (longClick != null && longClick.value() != 0) {
                View view = activity.findViewById(longClick.value());
                if (view == null) return;
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        try {
                            method.invoke(activity, v);
                        } catch (InvocationTargetException e) {
                            Log.e(TAG, "InvocationTargetException = " + e.toString());
                        } catch (IllegalAccessException e) {
                            Log.e(TAG, "IllegalAccessException = " + e.toString());
                        }
                        return true;
                    }
                });
            }
        }
    }
}
