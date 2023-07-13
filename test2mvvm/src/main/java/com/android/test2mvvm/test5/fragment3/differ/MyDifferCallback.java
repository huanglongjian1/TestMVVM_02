package com.android.test2mvvm.test5.fragment3.differ;

import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.DiffUtil;

import com.android.test2mvvm.util.Loge;

public class MyDifferCallback extends DiffUtil.Callback {
    ObservableArrayList<Teacher> old_observableArrayList, new_observableArrayList;

    public MyDifferCallback(ObservableArrayList<Teacher> old_observableArrayList, ObservableArrayList<Teacher> new_observableArrayList) {
        this.old_observableArrayList = old_observableArrayList;
        this.new_observableArrayList = new_observableArrayList;
    }

    @Override
    public int getOldListSize() {
        return old_observableArrayList.size();
    }

    @Override
    public int getNewListSize() {
        return new_observableArrayList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        // Loge.e(old_observableArrayList.get(oldItemPosition).toString() + "----" + new_observableArrayList.get(newItemPosition).toString());
        if (old_observableArrayList.get(oldItemPosition).getId() == new_observableArrayList.get(newItemPosition).getId()) {
            Loge.e("areItemsTheSame" + getCount++);
        }
        return old_observableArrayList.get(oldItemPosition).getId() == new_observableArrayList.get(newItemPosition).getId();
    }

    int getCount = 0;
    int count = 0;

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if (!(old_observableArrayList.get(oldItemPosition).getName().equals(new_observableArrayList.get(newItemPosition).getName())))
            Loge.e("areContentsTheSame次数" + count++);
        return old_observableArrayList.get(oldItemPosition).getName().equals(new_observableArrayList.get(newItemPosition).getName());
    }
}
