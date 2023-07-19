package com.android.test2mvvm.test6.basebottomsheet;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyDialogFragment_02 extends BottomSheetDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getContext());
        Loge.e(bottomSheetDialog.toString()+"==========");
        return bottomSheetDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        BottomSheetDialog bottomSheetDialog= (BottomSheetDialog) getDialog();
        Loge.e(bottomSheetDialog.toString()+"==========");
        bottomSheetDialog.getWindow().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
