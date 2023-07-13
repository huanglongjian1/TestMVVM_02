package com.android.test2mvvm.test4.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.android.test2mvvm.R;
import com.android.test2mvvm.base.BaseFragment;
import com.android.test2mvvm.util.Loge;

public class MyFragment2 extends BaseFragment {
    @Override
    protected int setResourceId() {
        return R.layout.test1_fragment8;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        Loge.e("AAAAAAAAAAAAAA");
        Bundle bundle = getArguments();
        if (bundle != null) Loge.e(bundle.getString("key"));

        Button textView = mView.findViewById(R.id.test1_fragment8_btn_delete);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Navigation.findNavController(v).navigate(R.id.one);
                //     Navigation.findNavController(getView()).popBackStack();
                Navigation.findNavController(getView()).navigate(R.id.one);
                //  Navigation.findNavController(getView()).navigate(R.id.action_one_to_three);
            }
        });
    }
}
