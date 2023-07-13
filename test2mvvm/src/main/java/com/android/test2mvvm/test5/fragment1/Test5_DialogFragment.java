package com.android.test2mvvm.test5.fragment1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.ui.AnimationUtil;

import java.util.Objects;

public class Test5_DialogFragment extends DialogFragment {
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Loge.e(context.toString());
    }

    View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加这句话去掉自带的标题栏
        Objects.requireNonNull(getDialog()).requestWindowFeature(Window.FEATURE_NO_TITLE);
        mRootView = inflater.inflate(R.layout.test5_fragment1_dialog, null);
        //从下到上的动画
        //AnimationUtil.slideToUp(mRootView);
        Loge.e(inflater.getContext().toString()+"-inflater");
        TextView textView=mRootView.findViewById(R.id.tv1);
        Loge.e(textView.getContext().toString()+"-textview");
        return mRootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = Objects.requireNonNull(getDialog()).getWindow();
        WindowManager.LayoutParams params = Objects.requireNonNull(window).getAttributes();
        //设置显示在底部
        params.gravity = Gravity.CENTER;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        View decorView = window.getDecorView();
        decorView.setPadding(100, 100, 100, 0);
        decorView.setBackground(new ColorDrawable(Color.TRANSPARENT));
        //设置点击空白处关闭，也能启动从上到下的动画
        decorView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    slideDown();
                    Loge.e(decorView.toString()+"-dec");
                }
                return true;
            }
        });
    }

    // 点击空白部分可以关闭
    private void slideDown() {
        Loge.e(mRootView.toString()+"-m");
        Loge.e(getView().toString()+"-g");
        dismiss();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Loge.e(super.onCreateDialog(savedInstanceState).toString());
        return super.onCreateDialog(savedInstanceState);
    }
}
