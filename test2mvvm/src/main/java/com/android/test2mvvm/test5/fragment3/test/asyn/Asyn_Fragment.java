package com.android.test2mvvm.test5.fragment3.test.asyn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;

import java.util.Random;

public class Asyn_Fragment extends Fragment {

    private ObservableArrayList<TestBean> mList = new ObservableArrayList();
    private AsyncListDifferAdapter mAsyncListDifferAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, null);
        RecyclerView recyclerView = view.findViewById(R.id.test_recyclerView);
        mAsyncListDifferAdapter = new AsyncListDifferAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAsyncListDifferAdapter);
        initData();
        TextView textView = view.findViewById(R.id.test_fragment_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
        view.findViewById(R.id.test_fragment_reset_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = new Random().nextInt(mAsyncListDifferAdapter.getItemCount());
                mAsyncListDifferAdapter.setData(position, new TestBean(position, "Item_" + position));
            }
        });
        return view;
    }

    int count;

    private void addData() {
        mAsyncListDifferAdapter.setData(new TestBean(count, "Item " + count));
        count++;
    }

    private void initData() {
        mList.clear();
        for (int i = 0; i < 10; i++) {
            mList.add(new TestBean(i, "Item " + i));
        }
        mAsyncListDifferAdapter.setData(mList);
    }
}
