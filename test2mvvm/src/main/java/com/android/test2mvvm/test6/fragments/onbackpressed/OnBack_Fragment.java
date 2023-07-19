package com.android.test2mvvm.test6.fragments.onbackpressed;

import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.onbackpressed.BackHandledFragment;
import com.android.test2mvvm.util.onbackpressed.FragmentBackHandler;

public class OnBack_Fragment extends BackHandledFragment {
    @Override
    public boolean onBackPressed() {
        Loge.e("fragment----onBackPressed");
        return true;
    }
}
