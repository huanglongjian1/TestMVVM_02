package com.android.test2mvvm.test6.test_fragment;

import com.android.test2mvvm.util.Loge;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WebApp {
    public void test() {
    }

    public void test(String string) {

    }

    public void app_invoke(Object object, Method method, Object... args) {
        try {
            method.invoke(object, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Object app_getObject(String class_name) {
        try {
            Class clazz = Class.forName(class_name);
            Method method = clazz.getDeclaredMethod("getInstance", null);
            Loge.e(class_name);
            Object object = method.invoke(null);
            return object;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Method app_getMethod(String class_name, String method_name, Object... objects) {
        try {
            Class clazz = Class.forName(class_name);
            Class[] classes1 = new Class[objects.length];
            for (int i = 0; i < objects.length; i++) {
                Class c = objects[i].getClass();
                classes1[i] = c;
                Loge.e(c.getName());
            }
            Method method = clazz.getMethod(method_name, classes1);

            return method;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object string_to_object(String string) {
        if (string.equals("true")) {
            return true;
        }
        if (string.equals("false")) {
            return false;
        }
        try {
          Integer integer= Integer.valueOf(string);
          return integer;
        } catch (Exception exception) {
            return string;
        }

    }

    public void webapp_invoke(String class_name, String method_name, Object... objects) {
        app_invoke(app_getObject(class_name), app_getMethod(class_name, method_name, objects), objects);
    }

}
