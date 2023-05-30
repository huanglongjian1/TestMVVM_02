package com.android.test2mvvm.test1.fragment10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.test1.fragment10.bean.News;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private LayoutInflater layoutInflater;
    private OnItemClickListener mListener; //item的点击监听器
    private List<News.NewslistBean> list;

    public interface OnItemClickListener {
        void onItemClickListener(News.NewslistBean news);
    }

    public NewsRecyclerAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.test1_fragment10_recyclerview_item, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {
        final News.NewslistBean news = list.get(position);
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(BR.news, news);
        binding.executePendingBindings();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClickListener(news);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void addAll(List<News.NewslistBean> news) {
        list.addAll(news);
    }
}
