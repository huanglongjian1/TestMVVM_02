package com.android.testmvvm.test5;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test5ActivityLayoutBinding;
import com.android.testmvvm.uitl.Constants;

@Route(path = Constants.TEST5_ACTIVITY)
public class Test5_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Test5ActivityLayoutBinding binding= DataBindingUtil.setContentView(this, R.layout.test5_activity_layout);
        binding.setViewModel(new LoginViewModel());
        binding.executePendingBindings();

    }
    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
