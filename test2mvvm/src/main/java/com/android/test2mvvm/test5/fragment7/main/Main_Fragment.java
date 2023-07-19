package com.android.test2mvvm.test5.fragment7.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.MainFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.fragment7.bean.User;
import com.android.test2mvvm.test5.fragment7.util.Constant;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.SharedPreferencesUtil;

import java.util.List;

public class Main_Fragment extends BaseFragment<MainFragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initView() {
        binding.test5FragmentQuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg("退出成功");
                SharedPreferencesUtil.setBooleanValue(getActivity(), Constant.IS_LOGIN, false);
                Navigation.findNavController(getView()).navigate(R.id.action_main_to_login);
            }
        });
        binding.test5FragmentClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_viewModel.clear();
                SharedPreferencesUtil.setBooleanValue(getActivity(), Constant.IS_LOGIN, false);
                Navigation.findNavController(getView()).navigate(R.id.action_main_to_login);
            }
        });
        binding.test5FragmentUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.update_fragment);
            }
        });
        binding.test5FragmentDialogBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.dialog_fragment));
    }

    Main_ViewModel main_viewModel;

    @Override
    protected void initData() {
        main_viewModel = new ViewModelProvider(getActivity()).get(Main_ViewModel.class);
        main_viewModel.getUser().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null) {
                    binding.test5FragmentMainTv.setText("欢迎光临" + users.toString());
                }
            }
        });

    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        NavController navController = NavHostFragment.findNavController(this);
//        // 获取当前NavBackStackEntry的SavedStateHandle的LiveData
//        MutableLiveData<String> liveData = navController.getCurrentBackStackEntry()
//                .getSavedStateHandle()
//                .getLiveData("key");
//        liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(String result) {
//                // 处理监听到的数据
//                Toast.makeText(getContext(), "result: " + result, Toast.LENGTH_SHORT).show();
//                Loge.e(result);
//            }
//        });
//    }
}
