package com.android.test2mvvm.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @作者 Gersy
 * @版本
 * @包名 com.gersion.pictureshow.base
 * @待完成
 * @创建时间 2016/11/21
 * @功能描述 TODO
 * @更新人 $
 * @更新时间 $
 * @更新版本 $
 */
public abstract class BaseFragment extends Fragment {

    protected View mView;
    protected Context mContext;
    protected boolean mIsVisible = false;
    protected ProgressDialog mProgressDialog;
    private Toast mToast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(setResourceId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
//        mContextComponent = DaggerContextComponent.builder().contextModule(new ContextModule(mContext)).build();
        initProgressDialog();
        initView();
        initEvent();

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mIsVisible = true;
            onVisible();

        } else {
            mIsVisible = false;
            onInVisible();
        }
    }

    protected void onInVisible() {

    }

    protected void onVisible() {
    }

    protected void initProgressDialog() {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("正在奋力的加载中...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
    }


    protected void showToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

    protected abstract int setResourceId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();
}
