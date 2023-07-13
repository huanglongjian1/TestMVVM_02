package com.android.test2mvvm.util.ui;

import com.android.test2mvvm.Test2_App;

import java.util.Properties;

public class PropertiesUtil {
    public static String load(String key) {
        String value = null;
        Properties properties = new Properties();
        try {
            properties.load(Test2_App.getInstance().getAssets().open("publish.properties"));
            value = properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}