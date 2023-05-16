package com.android.test2mvvm.test1.fragment5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test1Fragment5Binding;

import java.util.ArrayList;
import java.util.List;


public class Test1_Fragment5 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        List<User> mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setAge(String.valueOf(18 + i));
            user.setUserface("https://img-blog.csdnimg.cn/20190410160425701.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NoZjExNDIxNTIxMDE=,size_16,color_FFFFFF,t_70");
            user.setUserName("b" + i);
            user.setNickName("c" + i);
            mData.add(user);
        }
        Test1Fragment5Binding binding = DataBindingUtil.inflate(inflater, R.layout.test1_fragment5, container, false);


        binding.test1Fragment5RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        MvvmCommonAdapter adapter = new MvvmCommonAdapter(mData, BR.myUser, container.getContext(), R.layout.test1_fragment5_recyclerview_item);
        binding.setAdapter(adapter);


        return binding.getRoot();
    }
}
