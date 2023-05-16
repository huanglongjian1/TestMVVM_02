package com.android.testmvvm.test1;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test1ActivityLayoutBinding;
import com.android.testmvvm.uitl.Constants;

import java.util.Random;

@Route(path = Constants.TEST1_ACTIVITY)
public class Test1_Activity extends AppCompatActivity {
    Test1ActivityLayoutBinding binding;
    User user;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    setContentView(R.layout.test1_activity_layout);
        binding = DataBindingUtil.setContentView(this, R.layout.test1_activity_layout);
        binding.tvShow.setText("今晚吃鸡，大吉大利");
        user = new User("hlj", "123456");
        binding.setUser(user);
        binding.setHandler(new Handler());
        binding.btnChangeTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_test1, new Test1_Fragment()).commit();
            }
        });
    }

    public class Handler {
        public void onClick() {
            user.setPassword(String.valueOf(new Random().nextInt(10000)));
            user.setUsername(String.valueOf(new Random().nextInt(1000)));
        }
    }
}
