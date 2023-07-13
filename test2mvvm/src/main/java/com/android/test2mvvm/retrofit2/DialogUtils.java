package com.android.test2mvvm.retrofit2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.android.test2mvvm.R;

import java.lang.ref.WeakReference;

public class DialogUtils {
    Dialog dialog;
    private WeakReference<Context> reference;

    public DialogUtils() {

    }

    public void showProgress(Activity activity) {
        reference = new WeakReference<Context>(activity);
        dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.custom_sload_layout, null);
        dialog.setContentView(view);
        dialog.show();
    }

    public void dismissProgress() {
        if (reference!=null && reference.get() != null && dialog.isShowing() && dialog != null) {
            dialog.dismiss();
        }
    }
}
