package com.android.test2mvvm.test5.fragment2.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;

public class User_Adapter extends BaseAdapter {
    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.test5_fragment2_rv_item2;
    }

    @Override
    protected void onBindVH(RecyclerView.ViewHolder holder, int position) {
        Bean<User> bean = data_list.get(position);
        User user = bean.data;
        TextView name = holder.itemView.findViewById(R.id.test5_fragment2_tv_name);
        name.setText(user.name);
        TextView psw = holder.itemView.findViewById(R.id.test5_fragment2_tv_id);
        psw.setText(user.id + "-");
    }
}
