package com.android.test2mvvm.test1.fragment4;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test1Fragment4RecyclerviewItemBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<Book> bookList;

    public RecyclerViewAdapter(List<Book> books) {
        this.bookList = books;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Test1Fragment4RecyclerviewItemBinding layoutItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.test1_fragment4_recyclerview_item, parent, false);

        return new MyViewHolder(layoutItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.layoutItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Test1Fragment4RecyclerviewItemBinding layoutItemBinding;

        public MyViewHolder(Test1Fragment4RecyclerviewItemBinding itemView) {
            super(itemView.getRoot());
            layoutItemBinding = itemView;
        }
    }
}