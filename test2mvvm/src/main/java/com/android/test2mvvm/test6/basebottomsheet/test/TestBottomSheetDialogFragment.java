package com.android.test2mvvm.test6.basebottomsheet.test;

import android.app.Dialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.android.test2mvvm.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class TestBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private BottomSheetBehavior.BottomSheetCallback callback =
            new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        dismiss();
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sheet, null);
        dialog.setContentView(contentView);
        ListView listView = (ListView) contentView.findViewById(R.id.list_view);
        String[] strArray = new String[5];
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = "第" + (i + 1) + "项";
        }
        listView.setAdapter(new ArrayAdapter<String>(getContext()
                , android.R.layout.simple_list_item_1, android.R.id.text1, strArray));
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(callback);
        }
    }
}