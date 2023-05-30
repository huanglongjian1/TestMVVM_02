package com.android.test2mvvm.test2.adapter;

import android.content.Context;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.adapter.BaseRecyclerViewAdapter;
import com.android.test2mvvm.adapter.BindingViewHolder;

public class Test2Rv_Adapter extends BaseRecyclerViewAdapter {
    public Test2Rv_Adapter(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.test2_recyclerview_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.data, getList().get(position));
        holder.getBinding().executePendingBindings();
    }

}
