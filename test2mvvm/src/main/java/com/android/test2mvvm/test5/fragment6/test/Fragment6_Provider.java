package com.android.test2mvvm.test5.fragment6.test;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment5Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.util.Loge;

import java.util.Random;

public class Fragment6_Provider extends BaseFragment<Test5Fragment5Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment5;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        binding.test5Fragment5Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inData_String= String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
                Data indata = new Data.Builder().putString("work_key", inData_String).build();
                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(Test_Worker.class).setInputData(indata).build();
                WorkManager.getInstance(getContext()).enqueue(oneTimeWorkRequest);
                WorkManager.getInstance(getContext()).getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                        .observe(getViewLifecycleOwner(), new Observer<WorkInfo>() {
                            @Override
                            public void onChanged(WorkInfo workInfo) {
                                String work_key_string = workInfo.getOutputData().getString("work_key");
                                if (work_key_string != null) {
                                    Loge.e(work_key_string);
                                }

                            }
                        });

            }
        });
    }
}
