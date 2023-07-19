package com.android.test2mvvm.test6.basebottomsheet.test;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.BottomSheetLayoutBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class TestBehavior extends BaseFragment<BottomSheetLayoutBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.bottom_sheet_layout;
    }
    private BottomSheetBehavior mBottomSheetBehavior;
    @Override
    protected void initView() {
        View bottomSheet = getView().findViewById(R.id.bottom_sheet);
        Button button1 = (Button) getView().findViewById( R.id.button_1 );
        Button button2 = (Button) getView().findViewById( R.id.button_2 );
        Button button3 = (Button) getView().findViewById( R.id.button_3 );
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(120);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(1200);
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                mBottomSheetBehavior.setPeekHeight((int)slideOffset);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.button_1: {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            }
            case R.id.button_2: {
                mBottomSheetBehavior.setPeekHeight(120);
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            }
            case R.id.button_3: {
                BottomSheetDialogFragment bottomSheetDialogFragment = new TestBottomSheetDialogFragment();
                bottomSheetDialogFragment.show(getChildFragmentManager(), bottomSheetDialogFragment.getTag());
                break;
            }
        }
    }
}
