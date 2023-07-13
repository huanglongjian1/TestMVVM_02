package com.android.test2mvvm.test4;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.android.test2mvvm.R;
import com.android.test2mvvm.base.BaseFragment;
import com.android.test2mvvm.util.Loge;

public class MyFragment extends BaseFragment {
    @Override
    protected int setResourceId() {
        return R.layout.test2_frament1;
    }

    @Override
    protected void initView() {
        TextView textView = mView.findViewById(R.id.test2_fragment1_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "good");

                Navigation.findNavController(getView()).navigate(R.id.action_one_to_two, bundle);
            }
        });
        TextView textView2 = mView.findViewById(R.id.test2_fragment2_tv);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_one_to_three);
            }
        });
        TextView textView3 = mView.findViewById(R.id.test2_fragment3_tv);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_one_to_paging3);
            }
        });


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        Loge.e(getArguments().getString("key"));
    }
}
