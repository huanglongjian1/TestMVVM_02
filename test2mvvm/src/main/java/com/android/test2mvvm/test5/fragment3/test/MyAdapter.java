package com.android.test2mvvm.test5.fragment3.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter {
    private ArrayList<Student> data;

    ArrayList<Student> getData() {
        return data;
    }

    void setData(ArrayList<Student> data) {
        this.data = new ArrayList<>(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Student student = data.get(position);
        myViewHolder.tv.setText(student.getNum() + "." + student.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}