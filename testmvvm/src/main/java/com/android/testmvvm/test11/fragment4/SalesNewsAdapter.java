package com.android.testmvvm.test11.fragment4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.android.testmvvm.BR;
import com.android.testmvvm.R;

import java.util.ArrayList;
import java.util.List;

public class SalesNewsAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private List<SalesNewInfo> mSaleNewsList;
    private final LayoutInflater mLayoutInflater;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        /**
         * @time 2018/5/16  15:17
         * @describe 单个点击监听回调
         */
        void onsalesNewsClick(SalesNewInfo salesNews);
    }

    public SalesNewsAdapter(Context context) {
        mLayoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSaleNewsList = new ArrayList<>();
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        binding = DataBindingUtil.inflate(mLayoutInflater,
                R.layout.test11_fragment4_recyclerview_item, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final SalesNewInfo salesNewInfo = mSaleNewsList.get(position);
        holder.getBinding().setVariable(BR.item, salesNewInfo);
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onsalesNewsClick(salesNewInfo);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSaleNewsList.size();
    }

    public void addAll(List<SalesNewInfo> salesNews) {
        mSaleNewsList.addAll(salesNews);
    }

    public void add(SalesNewInfo salesNew) {
        mSaleNewsList.add(salesNew);
        notifyItemInserted(mSaleNewsList.size());
    }

    public void remove(int position) {
        mSaleNewsList.remove(position);
        notifyItemRemoved(position);
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
