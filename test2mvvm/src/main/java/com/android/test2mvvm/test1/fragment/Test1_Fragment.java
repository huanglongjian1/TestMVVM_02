package com.android.test2mvvm.test1.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test1FragmentRecyclerviewBinding;
import com.android.test2mvvm.test1.People;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class Test1_Fragment extends Fragment {
    @Inject
    public Test1_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Test1FragmentRecyclerviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.test1_fragment_recyclerview, container, false);
        binding.test1ActivityRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        List<People> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            People people = new People("https://img2.baidu.com/it/u=3323311628,2330835932&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=666", "十八姑娘一朵花", "二十五");
            list.add(people);
        }


        binding.setAdapter(new RecycleViewAdapter(getActivity(),list,R.layout.test1_fragment_recyclerview_item, BR.p));


        return binding.getRoot();
    }
}
