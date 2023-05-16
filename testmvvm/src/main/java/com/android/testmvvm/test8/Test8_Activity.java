package com.android.testmvvm.test8;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvvm.R;
import com.android.testmvvm.test8.item.DetailFragment;
import com.android.testmvvm.test8.item.MasterFragment;
import com.android.testmvvm.uitl.Constants;

@Route(path = Constants.TEST8_ACTIVITY)
public class Test8_Activity extends AppCompatActivity {
    private static final String TAG = "Demo2Activity";
    private Button mBtnAddData;
    private DemoViewModel mDemoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test8_activity_layout);
        mBtnAddData = findViewById(R.id.btn_test8);
        mDemoViewModel = new ViewModelProvider(this).get(DemoViewModel.class);//获取ViewModel,让ViewModel与此activity绑定
        mDemoViewModel.getDemoData().observe(this, new Observer<DemoData>() { //注册观察者,观察数据的变化
            @Override
            public void onChanged(DemoData demoData) {
                Log.e(TAG, "onChanged: 数据有更新");
                mBtnAddData.setText(demoData.getTag1() + "-");
            }
        });

        mBtnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: 已经点击");
                mDemoViewModel.getDemoData().setTag1(123); //这里手动用按键点击更新数据
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_one_test8, new MasterFragment())
                        .replace(R.id.fragment_two_test8, new DetailFragment()).commit();


            }
        });
    }
}
