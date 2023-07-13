package com.android.test2mvvm.test4.fragment1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerView extends RecyclerView {
    View mEmptyView;
    AdapterObserver mObserver;

    public void onAttach(View emptyView, AdapterObserver adapterObserver) {
        mEmptyView = emptyView;
        mObserver = adapterObserver;
    }

    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(mObserver);
            mObserver.onChanged();
        }
    }

    public void showEmptyView() {
        Adapter<?> adapter = getAdapter();
        if (adapter != null && mEmptyView != null) {
            if (adapter.getItemCount() == 0) {
                mEmptyView.setVisibility(VISIBLE);
                this.setVisibility(GONE);
            } else {
                mEmptyView.setVisibility(GONE);
                this.setVisibility(VISIBLE);
            }
        }
    }
}
