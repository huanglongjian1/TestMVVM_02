package com.android.test2mvvm.test2.fragment1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyViewPage2_Fragment_Adapter<T> extends FragmentStateAdapter {
    List<T> fragmentList;

    public MyViewPage2_Fragment_Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<T> list) {
        super(fragmentManager, lifecycle);
        fragmentList = list;
    }

    public void addData(T data) {
        fragmentList.add(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return Test2_Fragment1.newInstance("第" + position + "页");
    }

    @Override
    public int getItemCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }
}
