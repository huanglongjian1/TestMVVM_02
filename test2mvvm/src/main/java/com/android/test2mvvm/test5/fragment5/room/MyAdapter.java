package com.android.test2mvvm.test5.fragment5.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test5.fragment5.Concert;
import com.android.test2mvvm.util.Loge;

public class MyAdapter extends PagedListAdapter<Article, MyAdapter.MyVH> {
    protected MyAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test5_fragment5_rv_item, null);

        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        Article article = getItem(position);
        if (article != null){
            holder.textView.setText(article.toString());
        }else {

        }


    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView textView;

        public MyVH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.author);
        }
    }

    private static DiffUtil.ItemCallback<Article> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Article>() {

                @Override
                public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                    return oldItem.author.equals(newItem.author) &&
                            oldItem.desc.equals(newItem.desc) &&
                            oldItem.title.equals(newItem.title);
                }
            };
}
