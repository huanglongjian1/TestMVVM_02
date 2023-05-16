package com.android.testmvvm.test3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.RecyclerView;

import com.android.testmvvm.BR;
import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test3RecyclerviewItemLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Test3_RecyclerView_Adapter extends RecyclerView.Adapter<Test3_RecyclerView_Adapter.MyViewHolder> {
    List<ModelGirl> list;

    public void setData(List<ModelGirl> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Test3RecyclerviewItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.test3_recyclerview_item_layout, parent, false);


        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelGirl modelGirl = list.get(position);
       // holder.binding.setModelGirl(modelGirl);
        holder.binding.setVariable(BR.modelGirl,modelGirl);
        holder.binding.executePendingBindings();

     }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Test3RecyclerviewItemLayoutBinding binding;

        public MyViewHolder(@NonNull Test3RecyclerviewItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public Test3RecyclerviewItemLayoutBinding getBinding() {
            return binding;
        }

        public void setBinding(Test3RecyclerviewItemLayoutBinding binding) {
            this.binding = binding;
        }
    }
}
