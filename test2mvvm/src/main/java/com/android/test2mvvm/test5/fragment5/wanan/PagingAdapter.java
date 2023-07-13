package com.android.test2mvvm.test5.fragment5.wanan;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test5.fragment5.wanan.api.bean.DatasBean;
import com.android.test2mvvm.util.Loge;

public class PagingAdapter extends PagedListAdapter<DatasBean, PagingAdapter.ViewHolder> {

    public PagingAdapter() {
        super(itemCallback);
    }

    @NonNull
    @Override
    public PagingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test5_fragment5_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagingAdapter.ViewHolder holder, int position) {
        DatasBean bean = getItem(position);
        if (bean != null) {
            holder.title.setText(bean.getTitle());
            holder.author.setText(bean.getAuthor());
            holder.rv_content.setText(bean.getDesc());
        }
        holder.rv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("点击的项："+holder.getAbsoluteAdapterPosition());
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        TextView rv_content;


        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            rv_content = itemView.findViewById(R.id.rv_content);

        }
    }


    private static DiffUtil.ItemCallback<DatasBean> itemCallback = new DiffUtil.ItemCallback<DatasBean>() {
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