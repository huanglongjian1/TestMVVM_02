package com.android.test2mvvm.test5.fragment3.asyn_differ;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.android.test2mvvm.test5.fragment3.differ.Teacher;

public class MyAsyn_DifferCallback extends DiffUtil.ItemCallback<Teacher> {
    @Override
    public boolean areItemsTheSame(@NonNull Teacher oldItem, @NonNull Teacher newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Teacher oldItem, @NonNull Teacher newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull Teacher oldItem, @NonNull Teacher newItem) {
        return super.getChangePayload(oldItem, newItem);
    }
}
