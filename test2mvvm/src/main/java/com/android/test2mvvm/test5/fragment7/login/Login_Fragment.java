package com.android.test2mvvm.test5.fragment7.login;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.LoginFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.fragment7.bean.User;
import com.android.test2mvvm.test5.fragment7.bean.User_login;
import com.android.test2mvvm.test5.fragment7.util.Constant;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class Login_Fragment extends BaseFragment<LoginFragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.login_fragment;
    }

    @Override
    protected void initView() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_viewModel.user_loginMutableLiveData.getValue().getAccount().isEmpty()) {
                    showMsg("请输入账号");
                    return;
                }
                if (login_viewModel.user_loginMutableLiveData.getValue().getPwd().isEmpty()) {
                    showMsg("请输入密码");
                    return;
                }
                //检查输入的账户和密码是否是注册过的。
                checkUser();
            }
        });
        binding.gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister();
            }
        });
        binding.test5Fragment7LoginMvvm.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.login_to_room));

    }

    List<User> users = new ArrayList<>();

    private void checkUser() {

        User user = null;

        for (User user0 : users) {
            if (user0.getUid() == 1) {
                user = user0;
            }
        }

        if (user == null) {
            showMsg("还没注册，账号为空");
            return;
        }
        if (!login_viewModel.user_loginMutableLiveData.getValue().getAccount().equals(user.getAccount()) || !login_viewModel.user_loginMutableLiveData.getValue().getPwd().equals(user.getPwd())) {
            showMsg("账号或密码错误");
            return;
        }
        //记录已经登录过
        //  mvUtils.put(Constant.IS_LOGIN, true);
        SharedPreferencesUtil.setBooleanValue(getActivity(), Constant.IS_LOGIN, true);
        showMsg("登录成功");
        // jumpActivity(MainActivity.class);
        Navigation.findNavController(getView()).navigate(R.id.main_fragment);
    }

    Login_ViewModel login_viewModel;

    @Override
    protected void initData() {
        boolean islogin = SharedPreferencesUtil.getBooleanValue(getActivity(), Constant.IS_LOGIN, false);
        if (islogin) {
          //  Navigation.findNavController(getView()).navigate(R.id.action_login_to_main);
        }

        login_viewModel = new ViewModelProvider(getActivity()).get(Login_ViewModel.class);
        User_login user = new User_login("1", "1");
        login_viewModel.user_loginMutableLiveData.setValue(user);
        binding.setViewModel(login_viewModel);

        // login_viewModel.getLocalUser();
        login_viewModel.fail.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showMsg(s);

            }
        });

        login_viewModel.localUser.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users01) {
                users.clear();
                if (users01 != null) users.addAll(users01);
            }
        });
    }

    public void toRegister() {
        // jumpActivity(RegisterActivity.class);
        Navigation.findNavController(getView()).navigate(R.id.action_login_to_register);

    }
}
