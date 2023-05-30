package com.android.test2mvvm.test6.fragments.result.fragmenta;

import android.content.Context;
import android.view.View;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentARvItemBinding;
import com.android.test2mvvm.test5.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;

public class StringAdapter extends ObservableArrayList_BaseRecyclerViewAdapter<String, ResultFragmentARvItemBinding> {
    public StringAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.result_fragment_a_rv_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        String data = data_list.get(position);
        ResultFragmentARvItemBinding binding = (ResultFragmentARvItemBinding) holder.getBinding();
        binding.resultFragmentARecyclerviewItemTv.setText(data);
        binding.resultFragmentARecyclerviewItemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(data, holder.getAbsoluteAdapterPosition());
                }
            }
        });
    }

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(Object data, int position);
    }
}
