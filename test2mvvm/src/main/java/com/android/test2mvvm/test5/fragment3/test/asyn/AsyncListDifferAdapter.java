package com.android.test2mvvm.test5.fragment3.test.asyn;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;

import java.util.List;

public class AsyncListDifferAdapter extends RecyclerView.Adapter<AsyncListDifferAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    // 数据的操作由AsyncListDiffer实现
    private AsyncListDiffer<TestBean> mDiffer;

    public AsyncListDifferAdapter(Context mContext) {
        // 初始化AsyncListDiffe
        mDiffer = new AsyncListDiffer<>(this, new MyDiffUtilItemCallback());
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(TestBean mData) {
        ObservableArrayList<TestBean> mList = new ObservableArrayList<>();
        mList.addAll(mDiffer.getCurrentList());
        mList.add(mData);
        mDiffer.submitList(mList);
    }

    public void setData(int position, TestBean mData) {
        ObservableArrayList<TestBean> mList = new ObservableArrayList<>();
        mList.addAll(mDiffer.getCurrentList());
        //mList.add(mData);
        mList.set(position, mData);
        mDiffer.submitList(mList);
    }


    public void setData(ObservableArrayList<TestBean> mData) {
        // 由于DiffUtil是对比新旧数据，所以需要创建新的集合来存放新数据。
        // 实际情况下，每次都是重新获取的新数据，所以无需这步。
        ObservableArrayList<TestBean> mList = new ObservableArrayList<>();
        mList.addAll(mData);
        mDiffer.submitList(mList);
    }

    public void removeData(int index) {
        ObservableArrayList<TestBean> mList = new ObservableArrayList<>();
        mList.addAll(mDiffer.getCurrentList());
        mList.remove(index);
        mDiffer.submitList(mList);
    }

    public void clear() {
        mDiffer.submitList(null);
    }

    @Override
    @NonNull
    public AsyncListDifferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.itemview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            holder.mTvName.setText(bundle.getString("KEY_NAME"));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AsyncListDifferAdapter.ViewHolder holder, final int position) {
        TestBean bean = mDiffer.getCurrentList().get(position);
        holder.mTvName.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;

        ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.item_tv);
        }
    }
}