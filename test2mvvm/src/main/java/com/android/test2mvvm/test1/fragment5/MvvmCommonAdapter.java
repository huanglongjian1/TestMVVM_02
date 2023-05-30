package com.android.test2mvvm.test1.fragment5;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * RecyclerView 通用的 Adapter
 * <p>
 * Created by suwenlai on 16-12-27.
 */
public class MvvmCommonAdapter extends RecyclerView.Adapter<MvvmCommonAdapter.CommonHolder> {

    protected Context mContext;
    //所有 item 的数据集合
    protected List mDatas;
    //item 布局文件 id
    protected int mLayoutId;
    protected LayoutInflater mInflater;
    // mvvm绑定的viewModel引用
    private int mVariableId;

    //构造方法
    public MvvmCommonAdapter(List datas, int variableId, Context context, int layoutId) {
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
        mVariableId = variableId;
    }


    public List getmDatas() {
        return mDatas;
    }

    public void setmDatas(List mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), mLayoutId, parent, false);
        CommonHolder myHolder = new CommonHolder(binding.getRoot());
        myHolder.setBinding(binding);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(CommonHolder holder, int position) {
        holder.binding.setVariable(mVariableId,mDatas.get(position));
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }


    class CommonHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public CommonHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }
}