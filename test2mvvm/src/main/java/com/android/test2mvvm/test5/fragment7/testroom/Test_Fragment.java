package com.android.test2mvvm.test5.fragment7.testroom;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistryOwner;

import com.android.test2mvvm.R;
import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.databinding.Test5Fragment5Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.fragment7.bean.User;
import com.android.test2mvvm.util.ExecutorUtil;
import com.android.test2mvvm.util.Loge;

import java.util.List;

public class Test_Fragment extends BaseFragment<Test5Fragment5Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment5;
    }

    @Override
    protected void initView() {
        binding.test5Fragment5Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorUtil.execute(new Runnable() {
                    @Override
                    public void run() {
                        User user = new User(0, "1", "1", "1", "null", "null");
                        Test2_App.getDb().userDao().insert(user);
                    }
                });
            }
        });
        binding.test5Fragment5Tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test_viewModel.saveUser();
            }
        });
        binding.test5Fragment5BtnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  getActivity().finish();
             //   getActivity().getSupportFragmentManager().beginTransaction().remove(Test_Fragment.this).commit();
            }
        });
    }

    Test_ViewModel test_viewModel;

    @Override
    protected void initData() {
        LiveData<List<User>> user = Test2_App.getDb().userDao().getAll();
        user.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Loge.e(users.toString());
            }
        });


        test_viewModel = new ViewModelProvider(getActivity()).get(Test_ViewModel.class);

        binding.setViewmodel(test_viewModel);
        test_viewModel.userMutableLiveData.setValue(new User());

        test_viewModel.fail.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showMsg(s);
            }
        });
        test_viewModel.roomuser.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null) {
                    Loge.e(users.size() + "----");
                    Loge.e(users.toString());
                }

            }
        });

        test_viewModel.singleLiveEvent.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Loge.e("来自转换之后:" + users.toString());
            }
        });
        mMainViewModelLiveData = new ViewModelProvider(this).get(MainViewModelLiveData.class);
        binding.setData(mMainViewModelLiveData);
        binding.setLifecycleOwner(this);
    }

    MainViewModelLiveData mMainViewModelLiveData;

    @Override
    public void onPause() {
        super.onPause();
        Loge.e("退出");
        mMainViewModelLiveData.save();
    }
}
