package com.android.test2mvvm.test2.fragment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.test2mvvm.base.Base_new_LazyFragment;

import java.util.List;

public class MyPageAdapter extends FragmentPagerAdapter {
    List<Base_new_LazyFragment> fragmentList;

    public MyPageAdapter(@NonNull FragmentManager fm, int behavior, List<Base_new_LazyFragment> fragmentList) {
        super(fm, behavior);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "第" + position + "页";
    }
}
