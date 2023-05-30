package com.android.test2mvvm.test6.fragments.retrofit;


import com.android.test2mvvm.util.ExecutorUtil;
import com.android.test2mvvm.util.Loge;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.concurrent.Callable;

/**
 * 实现可以直接返回数据类的核心类
 */
public class DirectCallAdapterFactory extends CallAdapter.Factory {
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        final Type responseType = getResponseType(returnType);

        return new CallAdapter<Object, Object>() {
            public Type responseType() {
                return responseType;
            }

            public Object adapt(Call<Object> call) {
                // todo 可以在这里判断接口数据格式
                //                    Loge.e(Thread.currentThread().getName());
//                    return call.execute().body();
                return ExecutorUtil.submit(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return call.execute().body();
                    }
                });
            }
        };
    }

    private Type getResponseType(Type type) {
        if (type instanceof WildcardType) {
            return ((WildcardType) type).getUpperBounds()[0];
        }
        return type;
    }
}

