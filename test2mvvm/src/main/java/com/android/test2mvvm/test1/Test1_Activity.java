package com.android.test2mvvm.test1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.base.BaseActivity;
import com.android.test2mvvm.databinding.Test1ActivityLayoutBinding;
import com.android.test2mvvm.module.FragmentModule;
import com.android.test2mvvm.test1.fragment.Test1_Fragment;
import com.android.test2mvvm.test1.fragment2.Test2_fragment;
import com.android.test2mvvm.test1.fragment8.bean.User;
import com.android.test2mvvm.test1.fragment9.ui.login.LoginFragment;
import com.android.test2mvvm.test1.retrofit.BingImg;
import com.android.test2mvvm.util.retrofit.RetrofitManager;
import com.android.test2mvvm.test1.retrofit.SplashService;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
@Route(path = Constants.TEST1_ACTIVITY)
public class Test1_Activity extends AppCompatActivity {
    @Inject
    Test1_Fragment test1_fragment;
    @Inject
    User user;
    @Inject
    @FragmentModule.test1
    Test2_fragment test2_fragment;

    @Inject
    @FragmentModule.test2
    Test2_fragment test2_fragment1;

    public MutableLiveData<BingImg> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.test1_activity_layout);
        Test1ActivityLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.test1_activity_layout);
        List<People> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            People people = new People("https://img-blog.csdnimg.cn/20190410160425701.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NoZjExNDIxNTIxMDE=,size_16,color_FFFFFF,t_70", "十八姑娘一朵花", "二十五");
            list.add(people);
        }
        ListAdapter listAdapter = new ListAdapter(this, list, R.layout.test1_activity_listview_item, BR.people);
        binding.setAdapter(listAdapter);
        binding.test1ActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(user.toString());
                Loge.e(test2_fragment.toString());
                Loge.e(test2_fragment1.toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.test1_fragment, new LoginFragment()).commit();
            }
        });
        binding.test1FinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return", "the data of returning to FirstActivity");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        binding.test1FinishBtn.setTextSize(40);
        binding.test1LivedataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RetrofitManager.get("https://cn.bing.com/").create(SplashService.class)
//                        .getBingImgLiveData().observe(Test1_Activity.this, new Observer<javabean>() {
//                            @Override
//                            public void onChanged(javabean javabean) {
//                                Loge.e(javabean.getImages().get(0).getCopyright() + "-");
//                            }
//                        });
//
//                RetrofitManager.get("https://cn.bing.com/").create(SplashService.class)
//                        .getBingImgLiveData_observable().subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<javabean>() {
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(@NonNull javabean javabean) {
//                                Loge.e(javabean.getImages().get(0).getCopyright());
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//                                Loge.e(e.getMessage());
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
//                RetrofitManager.get("https://cn.bing.com/").create(SplashService.class)
//                        .getBingImg().enqueue(new Callback<BingImg>() {
//                            @Override
//                            public void onResponse(Call<BingImg> call, Response<BingImg> response) {
//                                // Loge.e(response.body().getImgUrl());
//                                mutableLiveData.setValue(response.body());
//                            }
//
//                            @Override
//                            public void onFailure(Call<BingImg> call, Throwable t) {
//                                throwableMutableLiveData.setValue(t);
//                            }
//                        });
                RetrofitManager.get("https://cn.bing.com/").create(SplashService.class)
                        .getBingImgLiveData_BingImg().observe(Test1_Activity.this, new Observer<BingImg>() {
                            @Override
                            public void onChanged(BingImg bingImg) {
                                //  Loge.e(bingImg.getImgUrl()+"测试bingImg");
                                mutableLiveData.setValue(bingImg);
                            }
                        });
            }
        });
        mutableLiveData.observe(this, new Observer<BingImg>() {
            @Override
            public void onChanged(BingImg bingImg) {
                Loge.e(bingImg.getImgUrl() + "测试viewmodle");
            }
        });
        throwableMutableLiveData.observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                Loge.e(throwable.getMessage() + "测试错误");
            }
        });
    }
}
