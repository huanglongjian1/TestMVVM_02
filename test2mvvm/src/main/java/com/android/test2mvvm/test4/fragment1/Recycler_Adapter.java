package com.android.test2mvvm.test4.fragment1;

import android.content.Context;

import com.android.test2mvvm.R;
import com.android.test2mvvm.adapter.BaseRecyclerViewAdapter;
import com.android.test2mvvm.adapter.BindingViewHolder;
import com.android.test2mvvm.databinding.RvRecyclerviewItemBinding;
import com.android.test2mvvm.util.Loge;

public class Recycler_Adapter extends BaseRecyclerViewAdapter<String> {
    public Recycler_Adapter(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.rv_recyclerview_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        String data = data_list.get(position);
        Loge.e(data);
        RvRecyclerviewItemBinding rvRecyclerviewItemBinding = (RvRecyclerviewItemBinding) holder.getBinding();
        rvRecyclerviewItemBinding.setData(data);
        // holder.getBinding().setVariable(BR.data, data);
        holder.getBinding().executePendingBindings();
    }

}
