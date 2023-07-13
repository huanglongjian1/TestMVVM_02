package com.android.test2mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.VH> {
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    class VH extends RecyclerView.ViewHolder{

        public VH(@NonNull View itemView,int layoutId) {
            super(LayoutInflater.from(itemView.getContext()).inflate(layoutId,null));

        }
    }
}
