package com.android.test2mvvm.util.onbackpressed;

import androidx.fragment.app.Fragment;

//没有处理back键需求的Fragment不用实现
public abstract class BackHandledFragment extends Fragment implements FragmentBackHandler {
    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }
}