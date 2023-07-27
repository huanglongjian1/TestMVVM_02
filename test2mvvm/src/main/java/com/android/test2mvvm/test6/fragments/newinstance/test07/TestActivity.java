package com.android.test2mvvm.test6.fragments.newinstance.test07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.test2mvvm.R;

public class TestActivity extends AppCompatActivity implements BackHandledInterface {
    private BackHandledFragment mBackHandedFragment;
    private boolean hadIntercept;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test5_activity);
        WorkFragment workFragment = new WorkFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.test5_fragment, workFragment).commit();

    }

    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }

    @Override
    public void onBackPressed() {
        if (mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                super.onBackPressed(); //退出
            } else {
                getSupportFragmentManager().popBackStack(); //fragment 出栈
            }
        }
    }
}
