package com.android.test2mvvm.test6.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.NavigationFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.Test6_Activity;
import com.android.test2mvvm.util.Loge;

public class Fragment07 extends BaseFragment<NavigationFragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.navigation_fragment;
    }

    @Override
    protected void initView() {
        getParentFragmentManager().setFragmentResultListener("msg", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Loge.e(result.getString("key") + "----kkkk");
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Test6_Activity test6_activity = (Test6_Activity) context;
        getLifecycle().addObserver(test6_activity.new FragmentLifecycle());
    }

    @Override
    public void onResume() {
        super.onResume();
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_fragment_test6_navigation);
        navController.setGraph(R.navigation.fragment7);
        if (getNavController != null) getNavController.onCreateNavController(navController);
    }

    GetNavController getNavController;

    public void setNavController(GetNavController getNavController) {
        this.getNavController = getNavController;
    }

    @Override
    protected void onDataLazyLoad() {
        Loge.e("lazeload");
    }
}
