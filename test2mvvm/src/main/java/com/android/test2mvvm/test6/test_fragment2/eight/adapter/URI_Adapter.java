package com.android.test2mvvm.test6.test_fragment2.eight.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentARvItemBinding;
import com.android.test2mvvm.databinding.Test5Fragment4RvItemBinding;
import com.android.test2mvvm.test5.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test6.test_fragment2.eight.Eight_Fragment_02;

public class URI_Adapter extends ObservableArrayList_BaseRecyclerViewAdapter<Uri, ResultFragmentARvItemBinding> {
    public URI_Adapter(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.result_fragment_a_rv_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        String data = data_list.get(position).toString();
        TextView textView = holder.itemView.findViewById(R.id.result_fragment_a_recyclerview_item_tv);
       if (data.length()>25){
           textView.setText(data.substring(0,12)+"...."+data.substring(data.length()-8,data.length()));
       }else {
           textView.setText(data);
       }

    }
}
