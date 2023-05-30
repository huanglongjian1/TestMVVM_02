package com.android.testmvvm.rxjavaroom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class GsonHelper {

    private GsonHelper() {
        // no instance
    }
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T toObject(String json, Class<T> classOfT)  {
        return gson.fromJson(json, classOfT);
    }

    public static <T> List<T> toList(String json, Class<? extends T[]> clazz) {
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }
}