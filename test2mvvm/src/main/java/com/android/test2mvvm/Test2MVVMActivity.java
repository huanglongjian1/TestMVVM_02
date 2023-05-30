package com.android.test2mvvm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.test2mvvm.test4.Test;
import com.android.test2mvvm.test4.fragment3.RecyclerViewActivity;
import com.android.test2mvvm.util.Constants;

public class Test2MVVMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        findViewById(R.id.main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constants.TEST5_ACTIVITY).navigation();
                // startActivity(new Intent(Test2MVVMActivity.this, Test.class));
                //  startActivity(new Intent(Test2MVVMActivity.this, RecyclerViewActivity.class));
            }
        });

    }
}