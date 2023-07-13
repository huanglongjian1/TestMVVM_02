package com.android.test2mvvm.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.R;
import com.android.test2mvvm.base.BaseActivity;
import com.android.test2mvvm.databinding.LoginActivityBinding;
import com.android.test2mvvm.util.Constants;

@Route(path = Constants.LOGIN_ACTIVITY)
public class Login_Activity extends BaseActivity<Login_ViewModel, LoginActivityBinding> {
    @Override
    protected int getContentViewId() {
        return R.layout.login_activity;
    }

    @Override
    protected void processLogic() {
        Intent intent = getIntent();
        String activity_name = intent.getStringExtra("context");

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = null;
                try {
                    if (activity_name != null && !TextUtils.isEmpty(activity_name)) {
                        intent1 = new Intent(Login_Activity.this, Class.forName(activity_name));
                    }
                    if (intent1 != null)
                        startActivity(intent1);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void registerViewModel() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
