package com.android.test2mvvm.test5.fragment5.paging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;

public class UserPagedListAdapter extends PagedListAdapter<UserInfo, UserPagedListAdapter.UserItemViewHolder> {

    private Context mContext;

    public UserPagedListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mContext = context;
    }

    private static DiffUtil.ItemCallback<UserInfo> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserInfo>() {


        @Override
        public boolean areItemsTheSame(@NonNull UserInfo oldItem, @NonNull UserInfo newItem) {
            return oldItem.getUserId() == newItem.getUserId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull UserInfo oldItem, @NonNull UserInfo newItem) {
//            return oldItem.getUserAvatar().equals(newItem.getUserAvatar()) &&
//                    oldItem.getUserName().equals(newItem.getUserName());
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserItemViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.test5_fragment5_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        //TODO
        TextView textView = holder.itemView.findViewById(R.id.author);
        if (getItem(position) != null) {
            textView.setText(getItem(position).toString());
        } else {
          //  textView.setText("null");
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(holder.getAbsoluteAdapterPosition() + "----" + textView.getText());
            }
        });
        Loge.e(getCurrentList().toString());

    }

    static class UserItemViewHolder extends RecyclerView.ViewHolder {

        public UserItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

