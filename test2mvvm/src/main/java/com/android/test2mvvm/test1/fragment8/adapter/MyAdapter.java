package com.android.test2mvvm.test1.fragment8.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test1.fragment8.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public static List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public void refreshList(List<User> list) {
        userList.clear();
        userList.addAll(list);

    }

    public void delete_Item(int position) {
        userList.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test1_fragment8_recyclerview_item, null);


        return new MyViewHolder(view);
    }

    public interface ItemLongClickListener {
        public void onItemLongClickListener(View view, int position);
    }

    ItemLongClickListener longClickListener;

    public void setOnItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        longClickListener = itemLongClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(userList.get(position).toString());
        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    longClickListener.onItemLongClickListener(v, holder.getAdapterPosition());
                }

                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.test1_fragment8_recyclerview_item_tv);
        }
    }

}
