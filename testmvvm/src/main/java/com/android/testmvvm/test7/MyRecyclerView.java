package com.android.testmvvm.test7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.android.testmvvm.BR;
import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test7RecyclerviewItemBinding;

import java.util.List;

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.ViewHolder> {
    private List<Information> informationList;
    private OnItemClickListener mOnItemClickListener;

    public MyRecyclerView(List<Information> informationList) {
        this.informationList = informationList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.test7_recyclerview_item, parent, false);
        return new ViewHolder(binding, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setVariable(BR.information, informationList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewDataBinding binding = null;
        OnItemClickListener listener;

        public ViewHolder(@NonNull ViewDataBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.listener = listener;
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
