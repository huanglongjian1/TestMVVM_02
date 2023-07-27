package com.android.test2mvvm.test6.test_fragment.util;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;

public class MyDialog extends DialogFragment {
    @Override
    public void onStart() {
        super.onStart();

        //设置动画、位置、宽度等属性（注意一：必须放在onStart方法中）
        Window window = getDialog().getWindow();
        if (window != null) {
            // 注意二：一定要设置Background，如果不设置，window属性设置无效
            window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));
            WindowManager.LayoutParams layoutParams = window.getAttributes();

            layoutParams.gravity = Gravity.CENTER; // 位置
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;//宽度满屏

            window.setAttributes(layoutParams);
        }

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//        此处context类型为Application Context
//        因此无法用来调用alertDialog
        alertDialog.setTitle("测试提醒");
        alertDialog.setMessage("你成功弹出了对话框");
        alertDialog.setCancelable(true);//设置取消按钮不可用
        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Loge.e("确定"+which);
                dialog.dismiss();
            }
        });
        alertDialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Loge.e("取消"+which);
                dialog.cancel();
            }
        });
        return alertDialog.create();
    }
}
