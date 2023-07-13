package com.android.test2mvvm.test5.fragment3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class MyDiffUtilItemCallback extends DiffUtil.ItemCallback<Goods> {

    /**
     * 是否是同一个对象
     */
    @Override
    public boolean areItemsTheSame(@NonNull Goods oldItem, @NonNull Goods newItem) {
        return oldItem.getDescribe() == newItem.getDescribe();
    }
    /**
     * 是否是相同内容
     */
    @Override
    public boolean areContentsTheSame(@NonNull Goods oldItem, @NonNull Goods newItem) {
        return oldItem.getDianzan().equals(newItem.getDianzan());
    }

    /**
     * areItemsTheSame()返回true而areContentsTheSame()返回false时调用,也就是说两个对象代表的数据是一条，但是内容更新了。此方法为定向刷新使用，可选。
     */
    @Nullable
    @Override
    public Object getChangePayload(@NonNull Goods oldItem, @NonNull Goods newItem) {
        Bundle payload = new Bundle();

        if (!oldItem.getDescribe().equals(newItem.getDescribe())) {
            payload.putString("describe", newItem.getDescribe());
        }
        if (!oldItem.getDianzan().equals(newItem.getDianzan())) {
            payload.putString("dianzan", newItem.getDianzan());
        }

        if (payload.size() == 0){
            //如果没有变化 就传空
            return null;
        }
        return payload;
    }
}