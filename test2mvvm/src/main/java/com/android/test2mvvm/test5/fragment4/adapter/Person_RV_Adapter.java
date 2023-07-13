package com.android.test2mvvm.test5.fragment4.adapter;

import android.content.Context;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment4RvItemBinding;
import com.android.test2mvvm.test5.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test5.fragment4.bean.Person;

public class Person_RV_Adapter extends ObservableArrayList_BaseRecyclerViewAdapter<Person, Test5Fragment4RvItemBinding> {
    public Person_RV_Adapter(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.test5_fragment4_rv_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.person, data_list.get(position));
    }
}
