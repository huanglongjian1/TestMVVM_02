package com.android.test2mvvm.test5.fragment6.aac;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;

import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewData> {

    private List<NewsDataVo> mData;

    DataAdapter(List<NewsDataVo> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewData(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test5_fragment5_rv_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewData viewData, int i) {
        NewsDataVo newsDataVo = mData.get(i);
        viewData.numTv.setText(newsDataVo.getReadNum() + "人阅读");
        viewData.titleTv.setText(newsDataVo.getNewsTitle());
        viewData.contentTv.setText(newsDataVo.getNewsContent());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewData extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleTv;
        private TextView contentTv;
        private TextView numTv;

        public ViewData(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_local);
            titleTv = itemView.findViewById(R.id.title);
            contentTv = itemView.findViewById(R.id.rv_content);
            numTv = itemView.findViewById(R.id.author);
        }
    }
}