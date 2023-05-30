package com.android.test2mvvm.test6.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.MobileFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.Test6_Activity;
import com.android.test2mvvm.util.Loge;

public class Mobile_Fragment extends BaseFragment<MobileFragmentBinding> implements NavHost{
    @Override
    protected int getLayoutId() {
        return R.layout.mobile_fragment;
    }

    @Override
    protected void initView() {
        Loge.e(this.toString());

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Test6_Activity test6_activity = (Test6_Activity) context;
        getLifecycle().addObserver(test6_activity.new FragmentLifecycle());
    }
    NavController navController;
    @Override
    public void onResume() {
        super.onResume();
         navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_fragment_test6_mobile);
        navController.setGraph(R.navigation.mobile_navigation);
        if (getNavController != null) getNavController.onCreateNavController(navController);
    }

    GetNavController getNavController;

    public void setNavController(GetNavController getNavController) {
        this.getNavController = getNavController;
    }

    @Override
    protected void initData() {

    }

    @Override
    public NavController getNavController() {
        if (navController!=null)return navController;
        return null;
    }
}
