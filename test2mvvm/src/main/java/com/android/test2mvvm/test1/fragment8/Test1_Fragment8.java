package com.android.test2mvvm.test1.fragment8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test1.fragment8.adapter.MyAdapter;
import com.android.test2mvvm.test1.fragment8.bean.User;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test1_Fragment8 extends Fragment {
    MyAdapter adapter;
    UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test1_fragment8, null);
        userViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        adapter = new MyAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.test1_fragment8_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        addObserver();
        Button add_btn = view.findViewById(R.id.test1_fragment8_btn_add);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
        Button clear_btn = view.findViewById(R.id.test1_fragment8_btn_clear);
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });
        adapter.setOnItemLongClickListener(new MyAdapter.ItemLongClickListener() {
            @Override
            public void onItemLongClickListener(View view, int position) {
                User user = adapter.getUserList().get(position);
                userViewModel.delete(user);
            }
        });


        return view;
    }

    public void addObserver() {
        userViewModel.getmAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Loge.e(users.size() + "-");
                adapter.refreshList((ArrayList<User>) users);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void addData() {
        User user = new User();
        user.setId(new Random().nextLong());
        user.setAge(25);
        user.setName("老张");
        user.setSex("男");
        userViewModel.addUser(user);
    }

    public void clearData() {
        userViewModel.delete_all();
    }


}
