package com.android.test2mvvm.test1.fragment2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.core.widget.NestedScrollView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

/**
 * Created by Phil on 2017/8/25.
 */

public class PhilView extends NestedScrollView {
    private static final String TAG = "调试";
    private static boolean isRefreshing = false;
    private static InverseBindingListener mInverseBindingListener;

    public PhilView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @BindingAdapter(value = "refreshing", requireAll = false)
    public static void setRefreshing(PhilView view, boolean refreshing) {
        if (isRefreshing == refreshing) {
            //防止死循环
            Log.d(TAG, "重复设置");
            return;
        } else {
            Log.d(TAG, "setRefreshing " + refreshing);
            isRefreshing = refreshing;
        }
    }

    @InverseBindingAdapter(attribute = "refreshing", event = "refreshingAttrChanged")
    public static boolean getRefreshing(PhilView view) {
        return isRefreshing;
    }

    @BindingAdapter(value = {"refreshingAttrChanged"}, requireAll = false)
    public static void setRefreshingAttrChanged(PhilView view, final InverseBindingListener inverseBindingListener) {
        Log.d(TAG, "setRefreshingAttrChanged");

        if (inverseBindingListener == null) {
            view.setRefreshingListener(null);
        } else {
            mInverseBindingListener = inverseBindingListener;
            view.setRefreshingListener(mOnRefreshingListener);
        }
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);

        if ((y < oldy) && y == 0) {
            if (isRefreshing) {
                Log.d(TAG, "正在刷新，请勿重复加载");
                return;
            } else {
                longTimeTask();
            }
        }
    }

    public void setRefreshingListener(OnRefreshingListener listener) {
        this.mOnRefreshingListener = listener;
    }

    public static abstract class OnRefreshingListener {
        public void startRefreshing() {
            isRefreshing = true;
            mInverseBindingListener.onChange();
        }

        public void stopRefreshing() {
            isRefreshing = false;
            mInverseBindingListener.onChange();
        }
    }

    private static OnRefreshingListener mOnRefreshingListener = new OnRefreshingListener() {
        @Override
        public void startRefreshing() {
            super.startRefreshing();
        }

        @Override
        public void stopRefreshing() {
            super.stopRefreshing();
        }
    };

    private void longTimeTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mOnRefreshingListener.startRefreshing();

                try {
                    //假设这里做了一个长时间的耗时操作
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mOnRefreshingListener.stopRefreshing();
            }
        }).start();
    }
}