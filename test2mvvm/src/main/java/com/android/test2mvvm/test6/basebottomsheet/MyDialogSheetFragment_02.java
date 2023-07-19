package com.android.test2mvvm.test6.basebottomsheet;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;

public class MyDialogSheetFragment_02 extends BaseFullBottomSheetFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.result_fragment_a_rv_item, null);
    }

    public static MyDialogSheetFragment_02 newInstance(Bundle bundle) {
        MyDialogSheetFragment_02 myDialogSheetFragment_02 = new MyDialogSheetFragment_02();
        myDialogSheetFragment_02.setArguments(bundle);
        return myDialogSheetFragment_02;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String args_string = bundle.getString(MyDialogSheetFragment_02.class.getSimpleName());
        TextView textView = view.findViewById(R.id.result_fragment_a_recyclerview_item_tv);
        textView.setText(args_string);

        setTopOffset(1500);
    }


}
