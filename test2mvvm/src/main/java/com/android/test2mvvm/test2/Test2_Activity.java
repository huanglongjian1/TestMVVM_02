package com.android.test2mvvm.test2;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.R;
import com.android.test2mvvm.adapter.BaseRecyclerViewAdapter;
import com.android.test2mvvm.base.BaseActivity;
import com.android.test2mvvm.base.Base_new_LazyFragment;
import com.android.test2mvvm.databinding.Test2ActivityBinding;
import com.android.test2mvvm.test2.adapter.Rv_Bean;
import com.android.test2mvvm.test2.adapter.Test2Rv_Adapter;
import com.android.test2mvvm.test2.adapter2.RV_Adapter2;
import com.android.test2mvvm.test2.bean.zhuangbi_bean;
import com.android.test2mvvm.test2.fragment1.MyPageAdapter;
import com.android.test2mvvm.test2.fragment1.MyViewPage2_Adapter;
import com.android.test2mvvm.test2.fragment1.MyViewPage2_Fragment_Adapter;
import com.android.test2mvvm.test2.fragment1.Test2_Fragment1;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.ui.PropertiesUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


@Route(path = Constants.TEST2_ACTIVITY)
public class Test2_Activity extends BaseActivity<Test2_ViewModel, Test2ActivityBinding> {
    Test2Rv_Adapter test2Rv_adapter;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected int getContentViewId() {
        return R.layout.test2_activity;
    }

    @Override
    protected void processLogic() {

        Loge.e(PropertiesUtil.load("baseUrl"));
        binding.setModel(mViewModel);
        binding.test2Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.get_baidu();
            }
        });
        binding.test2BtnBing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.get_Zhuangbi();
            }
        });

        test2Rv_adapter = new Test2Rv_Adapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.test2RecyclerView.setLayoutManager(linearLayoutManager);
        binding.test2RecyclerView.setAdapter(test2Rv_adapter);
        test2Rv_adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Object data, int position) {
                Loge.e(((Rv_Bean) data).toString());
            }
        });

        binding.test2ChangeRecyclerview.setOnClickListener(new View.OnClickListener() {
            boolean visibility_shou_btn = true;

            @Override
            public void onClick(View v) {
                binding.test2RecyclerView.setAdapter(null);
                RV_Adapter2 rv_adapter2 = new RV_Adapter2(Test2_Activity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Test2_Activity.this, LinearLayoutManager.VERTICAL, false);
                binding.test2RecyclerView.setLayoutManager(linearLayoutManager);
                binding.test2RecyclerView.setAdapter(rv_adapter2);

                mViewModel.visibility.postValue(visibility_shou_btn);
                visibility_shou_btn = !visibility_shou_btn;
            }
        });

        binding.test2Get12306Btn.setOnClickListener(new View.OnClickListener() {
            //            View view = null;
//
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction("OffLine");
//                sendBroadcast(intent);
//                RetrofitManager.get(ApiService.URL_BAIDU).create(ApiService.class).post_baidu_s("今晚打老虎")
//                        .compose(ApiTransformer.applySchedule()).subscribe(new BaseObserver<String>() {
//                            @Override
//                            public void onSuccess(String s) {
//                                String s1 = AESUtils.encrypt(s);
//                                Loge.e(s1);
//                                Loge.e(AESUtils.decrypt(s1));
//                            }
//
//                            @Override
//                            public void onFailure(Throwable e) {
//                                Loge.e(e.getMessage());
//                            }
//                        });
//                RetrofitManager.get(ApiService.URL_).create(ApiService.class).get_tngou(1).compose(ApiTransformer.applySchedule())
//                        .subscribe(new BaseObserver<String>() {
//                            @Override
//                            public void onSuccess(String s) {
//                                Loge.e(s);
//                            }
//
//                            @Override
//                            public void onFailure(Throwable e) {
//                                Loge.e(e.getMessage());
//                            }
//                        });
//                RetrofitManager.get(ApiService.URL_163).create(ApiService.class).postDataCall("JSON")
//                        .compose(ApiTransformer.applySchedule()).subscribe(new BaseObserver<Music_bean>() {
//                            @Override
//                            public void onSuccess(Music_bean music_bean) {
//                                Loge.e(music_bean.toString());
//                            }
//
//                            @Override
//                            public void onFailure(Throwable e) {
//                             Loge.e(e.getMessage());
//                            }
//                        });

//                ViewStub viewStub = findViewById(R.id.test2_view_stub);
//                if (viewStub != null) {
//                    view = viewStub.inflate();
//                }
//                // Loge.e(findViewById(R.id.test2_view_stub).toString());
//                //     Loge.e(findViewById(R.id.test2_view_stub_layout).toString());
//                /**
//                 * 旋转动画 rotation
//                 * */
//                Loge.e(view == null ? "yes" : "no");
//                ObjectAnimator rotationanimator = ObjectAnimator.ofFloat(view,
//                        "rotation", 0, 360);
//                rotationanimator.setDuration(2000);
//                rotationanimator.setRepeatCount(2);
//                rotationanimator.setRepeatMode(Animation.RESTART);
//                rotationanimator.start();
//                RetrofitManager.get(ApiService.URL_BAIDU).create(ApiService.class).get_baidu()
//                        .compose(ApiTransformer.applySchedule()).subscribe(new BaseObserver<String>() {
//                            @Override
//                            public void onSubscribe(Disposable disposable) {
//                                super.onSubscribe(disposable);
//                                compositeDisposable.add(disposable);
//                                Loge.e("开始订阅");
//                                mProgressDialog.show();
//                            }
//
//                            @Override
//                            public void onSuccess(String s) {
//                                mProgressDialog.dismiss();
//                                Loge.e(s);
//                            }
//
//                            @Override
//                            public void onFailure(Throwable e) {
//                                Loge.e(e.getMessage());
//                                mProgressDialog.dismiss();
//                            }
//
//
//                        });
//                compositeDisposable.add(Observable.interval(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Throwable {
//
//                    }
//                }));
//            }

                //    getSupportFragmentManager().beginTransaction().replace(R.id.test2_fragment, new Test2_Fragment1()).commit();
                MyViewPage2_Fragment_Adapter myViewPage2_fragment_adapter = (MyViewPage2_Fragment_Adapter) binding.test2Viewpage2Fragment.getAdapter();
                myViewPage2_fragment_adapter.addData("今晚打老虎");
            }
        });
    }

    @Override
    protected void registerViewModel() {
        mViewModel.getStringMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Loge.e(s);
            }
        });
        mViewModel.getFail().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Loge.e("失败的信息:" + s);
            }
        });
        mViewModel.zhuangbi_beanMutableLiveData.observe(this, new Observer<List<zhuangbi_bean>>() {
            @Override
            public void onChanged(List<zhuangbi_bean> zhuangbi_beans) {
                Loge.e(zhuangbi_beans.size() + ":数量");

                List<Rv_Bean> rv_beanList = new ArrayList<>();
                for (zhuangbi_bean bean : zhuangbi_beans) {
                    Loge.e(bean.toString());
                    Rv_Bean rv_bean = new Rv_Bean(bean.getImage_url(), bean.getDescription(), bean.getId() + "-");
                    rv_beanList.add(rv_bean);
                }

                BaseRecyclerViewAdapter baseRecyclerViewAdapter = (BaseRecyclerViewAdapter) binding.test2RecyclerView.getAdapter();
                baseRecyclerViewAdapter.addAll(rv_beanList);

            }
        });
    }

    @Override
    protected void initEvent() {
        viewpage2();
        viewpage2_fragment();
        viewpage_smartTabLayout();
    }

    private void viewpage_smartTabLayout() {
        List<Base_new_LazyFragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragmentList.add(Test2_Fragment1.newInstance("第"+i+"页"));
        }
      MyPageAdapter myPageAdapter=new MyPageAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragmentList);
      binding.test2Viewpage.setAdapter(myPageAdapter);
      binding.test2ViewpageSmartTabLayout.setViewPager(binding.test2Viewpage);

    }

    private void viewpage2_fragment() {
        List<String> fragmentList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragmentList.add("第" + i + "页");
        }
        MyViewPage2_Fragment_Adapter myViewPage2_fragment_adapter = new MyViewPage2_Fragment_Adapter(getSupportFragmentManager(), getLifecycle(), fragmentList);
        binding.test2Viewpage2Fragment.setAdapter(myViewPage2_fragment_adapter);
        new TabLayoutMediator(binding.test2TabViewpage2Fragment, binding.test2Viewpage2Fragment, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("第" + position + "页");

            }
        }).attach();
    }

    private void viewpage2() {
        MyViewPage2_Adapter myViewPage2_adapter = new MyViewPage2_Adapter();
        List<String> list = new ArrayList<>();
        list.add("第1页");
        list.add("第2页");
        list.add("第3页");
        myViewPage2_adapter.setList(list);
        binding.test2Viewpage2.setAdapter(myViewPage2_adapter);
        new TabLayoutMediator(binding.test2Viewpage2Tablayout, binding.test2Viewpage2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(list.get(position));

            }
        }).attach();
        binding.test2Viewpage2.setCurrentItem(2, false);
    }


    @Override
    protected void initData() {

    }

}
