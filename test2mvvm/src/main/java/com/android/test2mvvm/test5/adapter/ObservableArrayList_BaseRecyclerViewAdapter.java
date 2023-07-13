package com.android.test2mvvm.test5.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test5.fragment2.ListFactory;

public abstract class ObservableArrayList_BaseRecyclerViewAdapter<T, VDB extends ViewDataBinding> extends RecyclerView.Adapter<BindingViewHolder<VDB>> {
    private LayoutInflater layoutInflater;
    private OnItemClickListener mListener; //item的点击监听器
    protected ObservableArrayList<T> data_list;
    private int NORMAL_TYPE = 0;     // 第一种ViewType，正常的item
    private int FOOT_TYPE = 1;       // 第二种ViewType，底部的提示View

    public ObservableArrayList<T> getList() {
        return data_list;
    }

    public void delete(int position) {
        data_list.remove(position);
        //  notifyItemRangeRemoved(position,1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOT_TYPE;
        } else {
            return NORMAL_TYPE;
        }
    }


    public void reset(int position, T data) {
        data_list.set(position, data);
    }

    public void add(int position, T data) {
        data_list.add(position, data);
    }

    public void setData_list(ObservableArrayList<T> data_list) {
        this.data_list = data_list;
        data_list.addOnListChangedCallback(ListFactory.getListChangedCallback(this));
    }

    public interface OnItemClickListener {
        public void onItemClickListener(Object data, int position);
    }

    public interface OnFootClickListener {
        public void onFootClickListener();
    }

    OnFootClickListener onFootClickListener;

    public void setOnFootClickListener(OnFootClickListener onFootClickListener) {
        this.onFootClickListener = onFootClickListener;
    }

    public ObservableArrayList_BaseRecyclerViewAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data_list = new ObservableArrayList<>();
        data_list.addOnListChangedCallback(ListFactory.getListChangedCallback(this));
    }

    protected abstract int getRecyclerViewItemID();

    protected abstract void onBindVH(BindingViewHolder holder, int position);

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == NORMAL_TYPE) {
            ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, getRecyclerViewItemID(), parent, false);
            return new BindingViewHolder(binding);
        } else if (viewType == FOOT_TYPE) {
            ViewDataBinding footbinding = DataBindingUtil.inflate(layoutInflater, R.layout.rv_footer, parent, false);
            return new BindingViewHolder<>(footbinding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {
        if (position < data_list.size()) {
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

        } else {
            TextView textView = holder.itemView.findViewById(R.id.test5_tv_footer);
            if (TextUtils.isEmpty(textView.getText()))
                textView.setText("加载进行中");
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onFootClickListener != null) {
                        onFootClickListener.onFootClickListener();
                    }
                }
            });
        }
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data_list != null ? data_list.size() + 1 : 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void addAll(ObservableArrayList<T> datas) {
        //  data_list.clear();
        data_list.addAll(datas);
        //notifyDataSetChanged();
    }
}
