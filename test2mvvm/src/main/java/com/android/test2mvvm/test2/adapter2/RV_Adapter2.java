package com.android.test2mvvm.test2.adapter2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.adapter.BaseRecyclerViewAdapter;
import com.android.test2mvvm.adapter.BindingViewHolder;

public class RV_Adapter2 extends BaseRecyclerViewAdapter {
    public RV_Adapter2(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.test2_recyclerview_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.data,getList().get(position));
    }

}
