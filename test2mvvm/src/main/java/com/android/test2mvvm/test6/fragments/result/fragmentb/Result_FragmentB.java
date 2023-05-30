package com.android.test2mvvm.test6.fragments.result.fragmentb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentBBinding;
import com.android.test2mvvm.test4.Test4_Activity;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.fragments.result.EventObject;
import com.android.test2mvvm.test6.fragments.result.ViewModel_AB;
import com.android.test2mvvm.test6.fragments.result.fragmenta.Result_FragmentA;
import com.android.test2mvvm.util.Loge;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

public class Result_FragmentB extends BaseFragment<ResultFragmentBBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.result_fragment_b;
    }

    @Override
    protected void initView() {
        getParentFragmentManager().setFragmentResultListener(Result_FragmentA.class.getSimpleName(), this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Loge.e(requestKey);
                String position = String.valueOf(result.getInt("position", 0));
                String data = result.getString("data");
                binding.resultFragmentBTv.setText(data + "----" + position);
            }
        });

        binding.resultFragmentBTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("b_tv-被点击了:" + (String) binding.resultFragmentBTv.getText());
                Bundle bundle = new Bundle();
                bundle.putString("btv", (String) binding.resultFragmentBTv.getText());
                getParentFragmentManager().setFragmentResult("b", bundle);
            }
        });
        getParentFragmentManager().setFragmentResultListener("bbb", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Loge.e(result.getString("child", "没有取到数据"));
            }
        });
    }

    @Override
    protected void initData() {
        binding.resultFragmentBTv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Test4_Activity.class);
                startActivityForResult(intent, 1000);

                EventBus.getDefault().postSticky("测试eventbus----sticky");
            }
        });
        binding.resultFragmentBTvEventbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventObject eventObject = new EventObject();
                EventBus.getDefault().postSticky("sticky消息");
                eventObject.onDestroy();
            }
        });
        initViewModel();
    }

    private void initViewModel() {
        ViewModel_AB viewModel_ab = new ViewModelProvider(getActivity()).get(ViewModel_AB.class);
        binding.setDataab(viewModel_ab);
        binding.setLifecycleOwner(getActivity());
        binding.resultFragmentBSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel_ab.num.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Loge.e(requestCode + "==" + resultCode);
        if (requestCode == 1000 && resultCode == 1001) {

            Bundle bundle = data.getExtras();
            Loge.e("消息返回:" + Objects.requireNonNull(bundle).getString(Test4_Activity.class.getSimpleName(), "没有取到test4的消息"));

        }
    }
}
