package com.android.test2mvvm.util.retrofit;

import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManager {


    public static final String JSESSIONID = "JSESSIONID";
    //服务器请求baseUrl
    private static String sBaseUrl = null;

    //超时时长 默认10s
    private static int sConnectTimeout = 10;

    //用于存储retrofit
    private static ConcurrentHashMap<String, Retrofit> sRetrofitMap;

    private static OkHttpClient.Builder sOkHttpBuilder;

    //用于日期转换
    private static Gson sDataFormat;
    static HttpLoggingInterceptor loggingInterceptor;

    static {
        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        sRetrofitMap = new ConcurrentHashMap<>();


        sOkHttpBuilder = new OkHttpClient.Builder();
        //   sOkHttpBuilder.addInterceptor(Okhttp_Interceptor_Util.get_DataEncryptInterceptor());
        //超时时长
        sOkHttpBuilder.connectTimeout(sConnectTimeout, TimeUnit.SECONDS);

        sDataFormat = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    //初始化主要的baseUrl  便于后期直接获取
    public static void init(String baseUrl) {
        sBaseUrl = baseUrl;
    }

    public static String getsBaseUrl() {
        return sBaseUrl;
    }

    public static void setConnectTimeout(int connectTimeout) {
        sConnectTimeout = connectTimeout;
    }

    public static Retrofit get() {
        Retrofit retrofit = sRetrofitMap.get(sBaseUrl);
        if (retrofit == null) {
            throw new RuntimeException("BASE_URL为空");
        }
        return retrofit;
    }

    public static Retrofit get(String baseUrl) {
        Retrofit retrofit = sRetrofitMap.get(baseUrl);
        if (retrofit == null) {
            retrofit = createRetrofit(baseUrl);
            sRetrofitMap.put(baseUrl, retrofit);
        }
        return retrofit;
    }

    /**
     * 创建Retrofit对象
     *
     * @param baseUrl baseUrl
     * @return Retrofit
     */
    private static Retrofit createRetrofit(String baseUrl) {
        Okhttp_Interceptor_Util okhttp_interceptor_util = new Okhttp_Interceptor_Util();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(sOkHttpBuilder
                        .addInterceptor(loggingInterceptor)
                        // .addInterceptor(new CookieSaveInterceptor())
                        //   .addInterceptor(new CookieReadInterceptor())
                        //  .addInterceptor(new Okhttp_Interceptor_Util().get_DataEncryptInterceptor())
                        .addNetworkInterceptor(okhttp_interceptor_util.cacheIntercepter)
                        .cache(okhttp_interceptor_util.cache)
                        .build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                //  .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(sDataFormat))
                .build();
    }

    //存储Cookie拦截器
    public static class CookieSaveInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            Loge.e("是否有请求头");
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                HashSet<String> cookies = new HashSet<>();

                for (String header : originalResponse.headers("Set-Cookie")) {
                    cookies.add(header);
                    //    Loge.e("header:" + header);
                    if (header.contains("session")) {
                        String session = header.substring(header.indexOf("=") + 1, header.indexOf(";"));
                        SharedPreferencesUtil.setStringValue(Test2_App.getInstance(), JSESSIONID, session);
                        Loge.e(JSESSIONID + "：" + session);
                    }
                }


            }
            return originalResponse;
        }
    }

    //cookie 读取拦截器
    public static class CookieReadInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            builder.removeHeader("User-Agent")
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)");


            HashSet<String> stringSet = createRequestHeaders();
            for (String cookie : stringSet) {
                builder.addHeader("Cookie", cookie);
            }
            String session = SharedPreferencesUtil.getStringValue(Test2_App.getInstance(), JSESSIONID, "");
            Loge.e("session:" + session);
            //  builder.addHeader(JSESSIONID, session);
            builder.addHeader("Cookie", "JSESSIONID=227868448192F2A760875A5E097D0248; BIGipServerotn=1490616586.24610.0000; guidesStatus=off; highContrastMode=defaltMode; cursorStatus=off; BIGipServerpassport=988283146.50215.0000; fo=undefined; route=495c805987d0f5c8c84b14f60212447d");
            return chain.proceed(builder.build());
        }


    }

    private static HashSet<String> createRequestHeaders() {
        HashSet<String> hashSet = new HashSet<>();
        return hashSet;
    }

    /**
     * 通过okhttpClient来设置证书
     *
     * @param clientBuilder OKhttpClient.builder
     * @param certificates  读取证书的InputStream
     */
    public static void setCertificates(OkHttpClient.Builder clientBuilder, InputStream certificates) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException {
        try {
            Loge.e("证书安装");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            keyStore.setCertificateEntry("certificateAlias", certificateFactory
                    .generateCertificate(certificates));
            try {
                if (certificates != null)
                    certificates.close();
            } catch (IOException e) {
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:"
                        + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            clientBuilder.sslSocketFactory(sslSocketFactory, trustManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
