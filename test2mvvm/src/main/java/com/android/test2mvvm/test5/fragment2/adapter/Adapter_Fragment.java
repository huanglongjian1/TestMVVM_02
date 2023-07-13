package com.android.test2mvvm.test5.fragment2.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test5.fragment3.Loading;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test5_fragment3, null);
        RecyclerView recyclerView = view.findViewById(R.id.test5_fragment3_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        User_Adapter user_adapter = new User_Adapter();

        List<Bean> list = new ArrayList<>();
        list.add(new Bean(Bean.HEAD, null));
        for (int i = 0; i < 5; i++) {
            User user = new User("AAA" + i, i);
            Bean<User> userBean = new Bean<>();
            userBean.type = Bean.ITEM;
            userBean.data = user;
            list.add(userBean);
        }
        list.add(new Bean(Bean.FOOT, null));
        user_adapter.setData_list(list);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(user_adapter);
        user_adapter.setLoading(new Loading() {
            @Override
            public void onLoadMore() {
                Loge.e("more");
            }

            @Override
            public void refresh() {
                Loge.e("refresh");
            }
        });

        return view;
    }
}
