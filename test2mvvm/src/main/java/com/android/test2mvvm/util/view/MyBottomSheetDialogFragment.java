package com.android.test2mvvm.util.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.base.BaseFullBottomSheetFragment;

public class MyBottomSheetDialogFragment extends BaseFullBottomSheetFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.test1_fragment5, null);
        setTopOffset(1000);
        return view;
    }


}
