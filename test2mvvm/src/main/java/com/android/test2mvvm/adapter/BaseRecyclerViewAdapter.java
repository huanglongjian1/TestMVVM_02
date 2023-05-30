package com.android.test2mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindingViewHolder> {
    private LayoutInflater layoutInflater;
    private BaseRecyclerViewAdapter.OnItemClickListener mListener; //item的点击监听器
    protected List<T> data_list;

    public List<T> getList() {
        return data_list;
    }

    public void setData_list(List<T> data_list) {
        this.data_list = data_list;
    }

    public interface OnItemClickListener {
        public void onItemClickListener(Object data, int position);
    }

    public BaseRecyclerViewAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data_list = new ArrayList<>();
    }

    protected abstract int getRecyclerViewItemID();

    protected abstract void onBindVH(BindingViewHolder holder, int position);

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, getRecyclerViewItemID(), parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {
        T data = data_list.get(position);
        onBindVH(holder, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClickListener(data, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data_list != null ? data_list.size() : 0;
    }

    public void setOnItemClickListener(BaseRecyclerViewAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void addAll(List<T> datas) {
        data_list.clear();
        data_list.addAll(datas);
        notifyDataSetChanged();
    }
}
