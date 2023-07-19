package com.android.test2mvvm.test6.basebottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test6.basebottomsheet.test.BaseFull_Test;

public class MyDialogFragment_03 extends BaseFull_Test {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seekbar_fragment, null);
        return view;
    }
}
