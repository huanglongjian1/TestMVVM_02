package com.android.test2mvvm.util.retrofit;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import io.reactivex.rxjava3.annotations.NonNull;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class Okhttp_Interceptor_Util {
    public class DataEncryptInterceptor implements Interceptor {

        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();

            RequestBody oldBodyRequest = request.body();

            Buffer requestBuffer = new Buffer();
            oldBodyRequest.writeTo(requestBuffer);
            String oldBodyStr = requestBuffer.readUtf8();
            requestBuffer.close();

            Log.e("TAG", "the old body str is :" + oldBodyStr);

            //String randomKeyValue = "hello_" + System.currentTimeMillis() + "_world";
            String randomKeyValue = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
            String newBodyStr = AESUtils.encrypt(oldBodyStr, randomKeyValue);
            if (TextUtils.isEmpty(newBodyStr)) newBodyStr = "";
            Loge.e("加密之后:" + newBodyStr);
            MediaType mediaType = MediaType.parse("text/plain;charset=utf-8");
            RequestBody newRequestBody = RequestBody.create(mediaType, newBodyStr);

            //构建新的request
            Request newRequest = request.newBuilder().header("Content-type", newRequestBody.contentType().toString())
                    .header("Content-Length", String.valueOf(newRequestBody.contentLength()))
                    .method(request.method(), newRequestBody)
                    .header("key", randomKeyValue)
                    .build();

            Response response = chain.proceed(newRequest);
            if (response.code() / 200 == 1) {
                ResponseBody oldResponseBody = response.body();

                String oldResponseBodyStr = oldResponseBody.string();

                String newResponseBodyStr = AESUtils.decrypt(oldResponseBodyStr, randomKeyValue);
                if (TextUtils.isEmpty(newResponseBodyStr))
                    newResponseBodyStr = "data decrypy error";

                ResponseBody responseBody = ResponseBody.create(mediaType, newResponseBodyStr);

                response = response.newBuilder().body(responseBody).build();
            }
            return response;
        }
    }

    public DataEncryptInterceptor get_DataEncryptInterceptor() {
        return new DataEncryptInterceptor();
    }

    Interceptor cacheIntercepter = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //对request的设置用来指定有网/无网下所走的方式
            //对response的设置用来指定有网/无网下的缓存时长

            Request request = chain.request();
            if (!NetWorkUtil.isNetworkConnected(Test2_App.getInstance())) {
                //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                //有网络时则根据缓存时长来决定是否发出请求
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response response = chain.proceed(request);
            if (NetWorkUtil.isNetworkConnected(Test2_App.getInstance())) {
                //有网络情况下，超过1分钟，则重新请求，否则直接使用缓存数据
                int maxAge = 600; //缓存一分钟
                String cacheControl = "public,max-age=" + maxAge;
                //当然如果你想在有网络的情况下都直接走网络，那么只需要
                //将其超时时间maxAge设为0即可
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            } else {
                //无网络时直接取缓存数据，该缓存数据保存1周
                int maxStale = 60 * 60 * 24 * 7 * 1;  //1周
                return response.newBuilder()
                        .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                        .removeHeader("Pragma").build();
            }

        }
    };

    public File cacheFile = new File(Test2_App.getInstance().getExternalCacheDir(), "OKHttp3_Cache");//缓存地址
    public Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //大小50Mb


}
