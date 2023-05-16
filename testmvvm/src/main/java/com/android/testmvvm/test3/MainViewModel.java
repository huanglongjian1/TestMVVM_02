package com.android.testmvvm.test3;

import android.util.Log;

import com.android.testmvvm.databinding.Test3ActivityLayoutBinding;
import com.android.testmvvm.databinding.Test3RecyclerviewItemLayoutBinding;
import com.android.testmvvm.uitl.Loge;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel {
    private String imgurl;

    private ModelGirl modelGirl;
    public List<ModelGirl> urlList = new ArrayList<>();
    private Test3ActivityLayoutBinding binding;

    private final String TAG = "MainViewModel";

    public ModelGirl getModelGirl() {
        return modelGirl;
    }

    public void setModelGirl(ModelGirl modelGirl) {
        this.modelGirl = modelGirl;
    }

    public MainViewModel() {

    }

    public MainViewModel(Test3ActivityLayoutBinding binding, String imgurl) {
        this.binding = binding;
        this.imgurl = imgurl;
        initGirl();
    }

    //客户端不对服务器证书做任何验证
    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext.getSocketFactory();
    }

    //获得无需验证任何证书的OkHttpClient实例对象
    public static OkHttpClient getOkHttpClientInstance() {
        try {
            return new okhttp3.OkHttpClient.Builder()
                    .sslSocketFactory(getSSLSocketFactory(), new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    })
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();
        } catch (Exception e) {
            Log.e("OkHttpClientError", e.getMessage());
        }
        return null;
    }


    private void initGirl() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vvhan.com/")
                .client(getOkHttpClientInstance())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        GetImage_Interface request = retrofit.create(GetImage_Interface.class);

        Observable<ModelGirl> observable = request.getPic();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ModelGirl>() {
                    @Override
                    public void accept(ModelGirl modelGirl) throws Exception {
                        Log.i(TAG, "连接成功");
                        //Log.i(TAG, modelGirl.toString());
                        imgurl = modelGirl.getImgurl();
                        binding.setImgurl(imgurl);
                        urlList.add(modelGirl);
                        Loge.e(modelGirl.getImgurl());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "连接失败" + throwable.getMessage());
                    }
                });
    }

    public void onChange() {
        initGirl();
        Test3_RecyclerView_Adapter test3_recyclerView_adapter = (Test3_RecyclerView_Adapter) binding.recyclerViewTest3.getAdapter();
        test3_recyclerView_adapter.setData(urlList);
        binding.recyclerViewTest3.getAdapter().notifyDataSetChanged();
        Loge.e("点击我了");
    }
}