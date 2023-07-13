package com.android.test2mvvm.test5.fragment2.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test5.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.fragment3.Loading;
import com.android.test2mvvm.util.Loge;

import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Bean> data_list;

    public List<Bean> getData_list() {
        return data_list;
    }

    public void setData_list(List<Bean> data_list) {
        this.data_list = (List<Bean>) data_list;
    }

    protected abstract int getRecyclerViewItemID();

    protected abstract void onBindVH(RecyclerView.ViewHolder holder, int position);

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Bean.HEAD) {
            View head = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_head, null);
            return new HeadVH(head);
        } else if (viewType == Bean.ITEM) {
            View item = LayoutInflater.from(parent.getContext()).inflate(getRecyclerViewItemID(), null);
            return new ItemVH(item);
        } else {
            View foot = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_footer, null);
            return new FootVH(foot);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseAdapter.HeadVH) {
            BaseAdapter.HeadVH headVH = (HeadVH) holder;
            headVH.head_tv.setText("head");
            headVH.head_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (loading != null) {
                        loading.refresh();
                    }
                }
            });

        } else if (holder instanceof BaseAdapter.ItemVH) {
            onBindVH(holder, position);
        } else if (holder instanceof BaseAdapter.FootVH) {
            BaseAdapter.FootVH footVH = (FootVH) holder;
            footVH.foot_tv.setText("foot");
            footVH.foot_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (loading != null) {
                        loading.onLoadMore();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (position == 0) {
//            return Bean.HEAD;
//        } else if (position == getItemCount() - 1) {
//            return Bean.FOOT;
//        }
        Bean bean = (Bean) data_list.get(position);
        Loge.e(bean.type + "-");
        return bean.type;
    }

    public class HeadVH extends RecyclerView.ViewHolder {
        TextView head_tv;

        public HeadVH(@NonNull View itemView) {
            super(itemView);
            head_tv = itemView.findViewById(R.id.head_tv);
        }
    }

    public  class ItemVH extends RecyclerView.ViewHolder {
        public ItemVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class FootVH extends RecyclerView.ViewHolder {
        TextView foot_tv;
        ProgressBar progressBar;

        public FootVH(@NonNull View itemView) {
            super(itemView);
            foot_tv = itemView.findViewById(R.id.test5_tv_footer);
            progressBar = itemView.findViewById(R.id.test5_pb_footer_progressBar);
        }
    }

    Loading loading;

    public void setLoading(Loading loading) {
        this.loading = loading;
    }
}
