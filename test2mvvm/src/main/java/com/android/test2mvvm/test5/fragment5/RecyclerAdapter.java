package com.android.test2mvvm.test5.fragment5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;

public class RecyclerAdapter extends PagedListAdapter<Concert, RecyclerAdapter.RecyclerViewHolder> {

    protected RecyclerAdapter() {
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
        Concert concert = getItem(position);
        if (concert != null) {
            holder.mTitleTextView.setText(concert.getTitle());
            holder.mAuthorTextView.setText(concert.getAuthor());
            holder.mContentTextView.setText(concert.getContent());
        }
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTextView;
        TextView mAuthorTextView;
        TextView mContentTextView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.title);
            mAuthorTextView = itemView.findViewById(R.id.author);
            mContentTextView = itemView.findViewById(R.id.rv_content);
        }
    }

    private static DiffUtil.ItemCallback<Concert> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Concert>() {
                @Override
                public boolean areItemsTheSame(Concert oldConcert, Concert newConcert) {
                    return oldConcert.getTitle().equals(newConcert.getTitle());
                }

                @Override
                public boolean areContentsTheSame(Concert oldConcert,
                                                  Concert newConcert) {
                    return oldConcert.getContent().equals(newConcert.getContent());
                }
            };
}
