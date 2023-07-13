package com.android.test2mvvm.test4.fragment1;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterObserver extends RecyclerView.AdapterDataObserver {
    private MyRecyclerView mRecyclerView;

    public void onAttach(MyRecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    public void onChanged() {
        super.onChanged();
        mRecyclerView.showEmptyView();
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        super.onItemRangeInserted(positionStart, itemCount);
        mRecyclerView.showEmptyView();
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        super.onItemRangeRemoved(positionStart, itemCount);
        mRecyclerView.showEmptyView();
    }
}