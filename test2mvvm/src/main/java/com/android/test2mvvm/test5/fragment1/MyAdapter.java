package com.android.test2mvvm.test5.fragment1;

import android.content.Context;


import androidx.databinding.library.baseAdapters.BR;

import com.android.test2mvvm.R;
import com.android.test2mvvm.adapter.BaseRecyclerViewAdapter;
import com.android.test2mvvm.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.bean.Student;

public class MyAdapter extends BaseRecyclerViewAdapter<Student> {
    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.test5_fragment1_recyclerview_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        Student student = data_list.get(position);
        holder.getBinding().setVariable(BR.student, student);
    }
}
