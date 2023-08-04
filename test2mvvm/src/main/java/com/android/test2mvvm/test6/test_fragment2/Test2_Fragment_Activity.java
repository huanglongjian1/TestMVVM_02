package com.android.test2mvvm.test6.test_fragment2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;

public class Test2_Fragment_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test5_activity);
        //Loge.e(getIntent().getData().toString());
    }
}
