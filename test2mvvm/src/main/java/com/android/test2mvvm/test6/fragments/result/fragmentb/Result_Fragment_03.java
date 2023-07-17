package com.android.test2mvvm.test6.fragments.result.fragmentb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.basebottomsheet.MyDialogFragment_02;
import com.android.test2mvvm.test6.basebottomsheet.test.TestBottomSheetDialogFragment;
import com.android.test2mvvm.test6.fragments.bottomsheetdialog.MyBottomSheet_Fragment;
import com.android.test2mvvm.test6.fragments.dialog.MyDialogFragment;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Result_Fragment_03 extends BaseFragment<ResultFragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.result_fragment;
    }

    @Override
    protected void initView() {
        binding.resultFragmentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyDialogFragment myDialogFragment = new MyDialogFragment();
//                myDialogFragment.setType(1);
//                myDialogFragment.show(getParentFragmentManager(), "dialog");
//                BottomSheetDialog bottomSheetDialog = onCreateBottomSheetDialog();
//                bottomSheetDialog.show();
//                MyBottomSheet_Fragment myBottomSheet_fragment = new MyBottomSheet_Fragment();
//                myBottomSheet_fragment.show(getParentFragmentManager(), "dialog");
                //   myBottomSheet_fragment.setTopOffset(1500);
//                MyDialogFragment_02 myDialogFragment_02 = new MyDialogFragment_02();
//                myDialogFragment_02.show(getParentFragmentManager(), "dialog");

                TestBottomSheetDialogFragment testBottomSheetDialogFragment = new TestBottomSheetDialogFragment();
                testBottomSheetDialogFragment.show(getParentFragmentManager(),"dialog");
            }
        });
    }

    public BottomSheetDialog onCreateBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.seekbar_fragment, null);
        bottomSheetDialog.setContentView(view);
        SeekBar seekBar = view.findViewById(R.id.seekbar_test6);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Loge.e(String.valueOf(progress) + ":bottomsheetdialog进度条");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return bottomSheetDialog;

    }


    @Override
    protected void initData() {


    }

    Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new Result_Fragment_03();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activity = null;
    }
}
