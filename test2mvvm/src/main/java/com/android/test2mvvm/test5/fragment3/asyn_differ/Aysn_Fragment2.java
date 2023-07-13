package com.android.test2mvvm.test5.fragment3.asyn_differ;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test5.fragment2.adapter.Bean;
import com.android.test2mvvm.test5.fragment2.adapter.User;
import com.android.test2mvvm.test5.fragment2.adapter.User_Adapter;
import com.android.test2mvvm.test5.fragment2.adapter2.RecyclerWrapAdapter;

import com.android.test2mvvm.test5.fragment3.Loading;
import com.android.test2mvvm.test5.fragment3.differ.Teacher;
import com.android.test2mvvm.test5.fragment3.headandtail_rv.WrapRecyclerView;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;

public class Aysn_Fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, null);
        init_data();
        init_view(view);
        return view;
    }

    ObservableArrayList<Teacher> teacherObservableArrayList = new ObservableArrayList<>();

    private void init_data() {
        for (int i = 0; i < 50; i++) {
            teacherObservableArrayList.add(new Teacher(i, "teacter" + i));
        }
    }

    private void init_view(View view) {
        WrapRecyclerView wrapRecyclerView=view.findViewById(R.id.test5_adapter_wrap_rv);
        User_Adapter user_adapter=new User_Adapter();

        List<Bean> list = new ArrayList<>();
        list.add(new Bean(Bean.HEAD, null));
        for (int i = 0; i < 50; i++) {
            User user = new User("AAA" + i, i);
            Bean<User> userBean = new Bean<>();
            userBean.type = Bean.ITEM;
            userBean.data = user;
            list.add(userBean);
        }
        list.add(new Bean(Bean.FOOT, null));
        user_adapter.setData_list(list);

        View head=LayoutInflater.from(getContext()).inflate(R.layout.test_fragment,null);
        wrapRecyclerView.addHeaderView(head);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        wrapRecyclerView.setLayoutManager(linearLayoutManager);
        wrapRecyclerView.setAdapter(user_adapter);


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


    }
}
