package com.android.test2mvvm.test5.fragment5.book;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.squareup.picasso.Picasso;

public class RecyclerAdapter extends PagedListAdapter<Book, RecyclerAdapter.RecyclerViewHolder> {

    public RecyclerAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test5_fragment5_rv_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Book Book = getItem(position);
        if (Book != null) {
            holder.mTitleTextView.setText(Book.getTitle());
            holder.mAuthorTextView.setText(Book.getAuthor());
            holder.mContentTextView.setText(Book.getContent());
            Picasso.get().load(Book.img).placeholder(R.drawable.ic_launcher_background)
                    .error(R.mipmap.ic_launcher).centerCrop().resize(300, 300)
                    .into(holder.imageView);
        }
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTextView;
        TextView mAuthorTextView;
        TextView mContentTextView;
        ImageView imageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.title);
            mAuthorTextView = itemView.findViewById(R.id.author);
            mContentTextView = itemView.findViewById(R.id.rv_content);
            imageView = itemView.findViewById(R.id.iv_local);
        }
    }

    //需要oldBook与新 newBook 比较才能得出变化的数据
    private static DiffUtil.ItemCallback<Book> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Book>() {
                // 判断Item是否已经存在
                @Override
                public boolean areItemsTheSame(Book oldBook, Book newBook) {
                    return oldBook.getTitle().equals(newBook.getTitle());
                }

                // 如果Item已经存在则会调用此方法，判断Item的内容是否一致
                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Book oldBook, Book newBook) {
                    return oldBook.equals(newBook);
                }
            };
}