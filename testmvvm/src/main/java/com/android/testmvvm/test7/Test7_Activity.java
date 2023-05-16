package com.android.testmvvm.test7;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test7ActivityLayoutBinding;
import com.android.testmvvm.uitl.Constants;

@Route(path = Constants.TEST7_ACTIVITY)
public class Test7_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.test7_activity_layout);
        Test7ActivityLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.test7_activity_layout);
        ViewModel viewModel = new ViewModel(new Information(), binding.test7RecyclerView, this);
        binding.setViewmodel(viewModel);

    }
}
