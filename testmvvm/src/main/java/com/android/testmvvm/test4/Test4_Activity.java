package com.android.testmvvm.test4;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test4ActivityLayoutBinding;
import com.android.testmvvm.uitl.Constants;

@Route(path = Constants.TEST4_ACTIVITY)
public class Test4_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        askPermissions();
        super.onCreate(savedInstanceState);
        Test4ActivityLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.test4_activity_layout);
        MainVM vm = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainVM.class);
        binding.setVm(vm);
        //设置VM所使用的生命周期
        binding.setLifecycleOwner(this);
        vm.setBinding(binding, this);
    }

    private void askPermissions() {//动态申请权限！
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS,//联系人的权限
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};//读写SD卡权限
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }

    }
}
