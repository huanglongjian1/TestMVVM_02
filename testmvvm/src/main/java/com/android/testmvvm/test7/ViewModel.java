package com.android.testmvvm.test7;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewModel implements IViewModel {
    public Information information;
    public List<Information> mList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context context;
    private MyRecyclerView adapter;

    public ViewModel(Information information, RecyclerView recyclerView, Context context) {
        this.information = information;
        this.recyclerView = recyclerView;
        this.context = context;
        InitRecyclerView();
        InitData();
        InitListener();
    }

    private void InitRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyRecyclerView(mList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void InitData() {
        for (int i = 0; i < 10; i++) {
            mList.add(new Information("张三" + i, "男", i + ""));
            Log.d("TAG", mList.get(i).mName + "");
        }
        information.setInformationList(mList);
    }

    @Override
    public void InitListener() {
        adapter.setOnItemClickListener(new MyRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context, "位数：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
