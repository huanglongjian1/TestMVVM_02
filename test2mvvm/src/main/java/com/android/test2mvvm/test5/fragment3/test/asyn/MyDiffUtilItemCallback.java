package com.android.test2mvvm.test5.fragment3.test.asyn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.android.test2mvvm.util.Loge;

public class MyDiffUtilItemCallback extends DiffUtil.ItemCallback<TestBean> {

    /**
     * 是否是同一个对象
     */
    @Override
    public boolean areItemsTheSame(@NonNull TestBean oldItem, @NonNull TestBean newItem) {
        return oldItem.getId() == newItem.getId();
    }
    /**
     * 是否是相同内容
     */
    @Override
    public boolean areContentsTheSame(@NonNull TestBean oldItem, @NonNull TestBean newItem) {
       Loge.e(String.valueOf(oldItem.getName().equals(newItem.getName())));
        return oldItem.getName().equals(newItem.getName());
    }

    /**
     * areItemsTheSame()返回true而areContentsTheSame()返回false时调用,也就是说两个对象代表的数据是一条，但是内容更新了。此方法为定向刷新使用，可选。
     */
    @Nullable
    @Override
    public Object getChangePayload(@NonNull TestBean oldItem, @NonNull TestBean newItem) {
        Bundle payload = new Bundle();
        Loge.e(oldItem.toString());
        if (!oldItem.getName().equals(newItem.getName())) {
            payload.putString("KEY_NAME", newItem.getName());
        }

        if (payload.size() == 0){
            //如果没有变化 就传空
            return null;
        }
        return payload;
    }
}