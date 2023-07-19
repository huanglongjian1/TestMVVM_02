package com.android.test2mvvm.test6.fragments.bottomsheetdialog;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

public class MyBottomSheet_Fragment extends BaseFullBottomSheetFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seekbar_fragment, null);
        return view;
    }
    long mLastPressBackTime=0, INTERVAL_OF_TWO_CLICK_TO_QUIT=2000;
    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Loge.e("返回");
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (System.currentTimeMillis() - mLastPressBackTime < INTERVAL_OF_TWO_CLICK_TO_QUIT) {
                        getActivity().finish();
                    } else {
                        Loge.e("再按一次退出程序");
                        mLastPressBackTime = System.currentTimeMillis();
                    }
                }
                return true;
            }
        });
    }
}
