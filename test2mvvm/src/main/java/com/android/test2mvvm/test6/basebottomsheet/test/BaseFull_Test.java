package com.android.test2mvvm.test6.basebottomsheet.test;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

public class BaseFull_Test extends BaseFullBottomSheetFragment {
    @Override
    public void onStart() {
        super.onStart();
        MyBehavior myBehavior = new MyBehavior();
        ViewGroup fragment = (ViewGroup) getView().getParent();
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fragment.getLayoutParams();
        //  Loge.e(layoutParams.getBehavior().toString()+"--------====-----"+myBehavior);
        layoutParams.setBehavior(myBehavior);
    }
}
