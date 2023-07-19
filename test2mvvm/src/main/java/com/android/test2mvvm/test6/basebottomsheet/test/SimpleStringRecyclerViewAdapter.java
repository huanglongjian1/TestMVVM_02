package com.android.test2mvvm.test6.basebottomsheet.test;

import android.content.Context;
import android.widget.TextView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentARvItemBinding;
import com.android.test2mvvm.test5.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;

public class SimpleStringRecyclerViewAdapter extends ObservableArrayList_BaseRecyclerViewAdapter<String,ResultFragmentARvItemBinding> {
    public SimpleStringRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.result_fragment_a_rv_item;
    }


    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.result_fragment_a_recyclerview_item_tv);
        textView.setText(data_list.get(position));
    }

}
