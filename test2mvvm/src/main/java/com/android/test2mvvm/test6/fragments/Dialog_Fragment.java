package com.android.test2mvvm.test6.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.DialogFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.MessageListener;
import com.android.test2mvvm.test6.Test6_Activity;
import com.android.test2mvvm.util.Loge;

public class Dialog_Fragment extends BaseFragment<DialogFragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_fragment;
    }

    @Override
    protected void initView() {

        binding.test6DialogTv.setOnClickListener(new View.OnClickListener() {
            int anInt = 0;

            @Override
            public void onClick(View v) {
                MessageListener messageListener = (MessageListener) getActivity();
                messageListener.getMessageFromFragment(Dialog_Fragment.this, "来自dialogFragment的消息" + anInt++);

                Bundle result = new Bundle();
                result.putString("key", "result");
                getParentFragmentManager().setFragmentResult("msg", result);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Test6_Activity test6_activity = (Test6_Activity) context;
        getLifecycle().addObserver(test6_activity.new FragmentLifecycle());
    }

    @Override
    public void onResume() {
        Loge.e("================onResume");
        super.onResume();
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_fragment_test6_dialog);
        navController.setGraph(R.navigation.navigation_dialog);
        if (getNavController != null) getNavController.onCreateNavController(navController);

    }

    GetNavController getNavController;

    public void setNavController(GetNavController getNavController) {
        this.getNavController = getNavController;
    }


    @Override
    protected void initData() {

    }
}
