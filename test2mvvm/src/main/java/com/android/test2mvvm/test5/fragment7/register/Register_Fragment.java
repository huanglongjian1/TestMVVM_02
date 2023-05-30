package com.android.test2mvvm.test5.fragment7.register;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.RegisterFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.fragment7.bean.User;
import com.android.test2mvvm.util.Loge;

public class Register_Fragment extends BaseFragment<RegisterFragmentBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.register_fragment;
    }

    @Override
    protected void initView() {
        binding.btnRegister.setOnClickListener(v -> {
            if (registerViewModel.user.getValue().getAccount().isEmpty()) {
                showMsg("请输入账号");
                return;
            }
            if (registerViewModel.user.getValue().getPwd().isEmpty()) {
                showMsg("请输入密码");
                return;
            }
            if (registerViewModel.user.getValue().getConfirmPwd().isEmpty()) {
                showMsg("请确认密码");
                return;
            }
            if (!registerViewModel.user.getValue().getPwd().equals(registerViewModel.user.getValue().getConfirmPwd())) {
                showMsg("两次输入密码不一致");
                return;
            }

            registerViewModel.register();
            registerViewModel.fail.observe(this, failed -> {
                showMsg("200".equals(failed) ? "注册成功" : failed);
                if ("200".equals(failed)) {
                    Loge.e("转到login");
                    Navigation.findNavController(getView()).navigate(R.id.action_register_to_login);
                }
            });
        });

    }

    RegisterViewModel registerViewModel;

    @Override
    protected void initData() {
        registerViewModel = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);
        registerViewModel.getUser().setValue(new User(0, "1", "1", "1", "null", "null"));
        binding.setRegister(registerViewModel);
    }
}
