package com.android.test2mvvm.test4.fragment2;

import android.content.Context;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.adapter.BaseRecyclerViewAdapter;
import com.android.test2mvvm.adapter.BindingViewHolder;

public class Fragment2_Adapter extends BaseRecyclerViewAdapter<User> {


    public Fragment2_Adapter(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.test4_fragment2_rv_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        User user = data_list.get(position);
        holder.getBinding().setVariable(BR.user,user);
        holder.getBinding().executePendingBindings();
    }
}
