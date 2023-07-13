package com.android.test2mvvm.util.ui;


import android.view.View;
import android.view.animation.AlphaAnimation;

import com.android.test2mvvm.util.Loge;

public class AnimationUtil {
    public static void showAlphaAnimation(View v, long durationMillis) {
        Loge.e("动画");
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(durationMillis);
        v.startAnimation(animation);
    }

    public static void hideAlphaAnimation(View v, long durationMillis) {
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(durationMillis);
        v.startAnimation(animation);
    }
}
