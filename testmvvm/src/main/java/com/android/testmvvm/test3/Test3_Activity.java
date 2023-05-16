package com.android.testmvvm.test3;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test3ActivityLayoutBinding;
import com.android.testmvvm.uitl.Constants;

@Route(path = Constants.TEST3_ACTIVITY)
public class Test3_Activity extends AppCompatActivity {
    Test3ActivityLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test3_activity_layout);
        binding.setMainviewmodel(new MainViewModel(binding, ""));

        binding.recyclerViewTest3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Test3_RecyclerView_Adapter test3_recyclerView_adapter = new Test3_RecyclerView_Adapter();
        binding.recyclerViewTest3.setAdapter(test3_recyclerView_adapter);
    }
}
