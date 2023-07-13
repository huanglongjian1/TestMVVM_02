package com.android.test2mvvm.test5.fragment3;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.AsyncListDiffer;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment3RvItemBinding;
import com.android.test2mvvm.test5.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.util.Loge;

import java.util.List;

public class Fragment3_Adapter extends ObservableArrayList_BaseRecyclerViewAdapter<Goods, Test5Fragment3RvItemBinding> {
    private AsyncListDiffer<Goods> mDiffer;

    public Fragment3_Adapter(Context context) {
        super(context);
        mDiffer = new AsyncListDiffer<>(this, new MyDiffUtilItemCallback());
        //mDiffer.submitList(data_list);
    }

    public void setDiffer_Data(Goods mData) {
        ObservableArrayList<Goods> mList = new ObservableArrayList<>();
        mList.addAll(mDiffer.getCurrentList());
        mList.add(mData);
        mDiffer.submitList(mList);
    }

    public void setDiffer_Data(ObservableArrayList<Goods> mData) {
        // 由于DiffUtil是对比新旧数据，所以需要创建新的集合来存放新数据。
        // 实际情况下，每次都是重新获取的新数据，所以无需这步。
        ObservableArrayList<Goods> mList = new ObservableArrayList<>();
        mList.addAll(mData);
        mDiffer.submitList(mList);
    }

    public void removeData(int index) {
        ObservableArrayList<Goods> mList = new ObservableArrayList<>();
        mList.addAll(mDiffer.getCurrentList());
        mList.remove(index);
        mDiffer.submitList(mList);
    }

    public void clear() {
        mDiffer.submitList(null);
    }


    public AsyncListDiffer<Goods> getmDiffer() {
        return mDiffer;
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.test5_fragment3_rv_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        Goods goods = data_list.get(position);
        Loge.e(goods.toString());
        Test5Fragment3RvItemBinding test5Fragment3RvItemBinding = (Test5Fragment3RvItemBinding) holder.getBinding();
        holder.getBinding().setVariable(BR.goods, goods);

        test5Fragment3RvItemBinding.test5Fragment3DianzanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(String.valueOf(goods.dz));
                Bundle bundle = new Bundle();
                if (!goods.dz) {
                    goods.setDianzan("被点赞了");
                    bundle.putString("dianzan", "被点赞了");
                    notifyItemChanged(position, bundle);//单项局部刷新，一般用在单项 带图片上，payload 起名一般 用前面bean字段名
                    //  reset(position, goods);    单项刷新，单项不带图片的话，可以偷懒直接用这个。
                    // notifyDataSetChanged(); 懒人直接用这个，单容易挨 上面叼，用户体验极差
                    goods.dz = true;
                } else {
                    goods.setDianzan("取消了点赞");
                    bundle.putString("dianzan", "取消了点赞");
                    notifyItemChanged(position, bundle);
                    // reset(position, goods);
                    goods.dz = false;
                }
            }
        });
        test5Fragment3RvItemBinding.test5Fragment3DescribeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("describe", "点击了");
                notifyItemChanged(position, bundle);
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<Test5Fragment3RvItemBinding> holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);

            if (!TextUtils.isEmpty(bundle.getString("dianzan"))) {
                holder.getBinding().test5Fragment3DianzanTv.setText(bundle.getString("dianzan"));
            } else if (!TextUtils.isEmpty(bundle.getString("describe"))) {
                holder.getBinding().test5Fragment3DescribeTv.setText(bundle.getString("describe"));
            }
        }
    }
}
