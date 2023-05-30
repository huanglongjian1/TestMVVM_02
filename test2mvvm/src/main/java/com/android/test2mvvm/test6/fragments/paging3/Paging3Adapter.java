package com.android.test2mvvm.test6.fragments.paging3;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test6.fragments.paging3.bean.DatasBean;
import com.android.test2mvvm.util.Loge;

public class Paging3Adapter extends PagingDataAdapter<DatasBean, Paging3Adapter.ViewHolder> {


    public Paging3Adapter() {
        super(itemCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        return new Paging3Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       DatasBean bean = getItem(position);
        if (bean != null) {
            holder.desc.setText(bean.getDesc());
            holder.time.setText(String.valueOf(bean.getPublishTime()));
            holder.type.setText(bean.getType()+"-");
            holder.auth.setText(bean.getAuthor());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(bean.toString());
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView desc;
        TextView time;
        TextView type;
        TextView auth;

        public ViewHolder(View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.desc);
            time = itemView.findViewById(R.id.time);
            type = itemView.findViewById(R.id.type);
            auth = itemView.findViewById(R.id.auth);
        }
    }


    public static DiffUtil.ItemCallback<DatasBean> itemCallback = new DiffUtil.ItemCallback<DatasBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull DatasBean oldItem, @NonNull DatasBean newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull DatasBean oldItem, @NonNull DatasBean newItem) {
            return oldItem.equals(newItem);
        }
    };
}