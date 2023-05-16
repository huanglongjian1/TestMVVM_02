package com.android.testmvvm.test11.fragment5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;

import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test11Fragment5ListviewBinding;

import java.util.ArrayList;

public class Test5_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Test11Fragment5ListviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.test11_fragment5_listview, container, false);
//        ArrayList<People> peoples = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            peoples.add(new People("https://img2.baidu.com/it/u=3323311628,2330835932&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=666"
//                    , "好人" + i, i * 2));
//        }
//        binding.setAdapter(new ListAdapter<People>(getActivity(), peoples, R.layout.test11_fragment5_listview_item, BR.adapter));
//
//
//        return binding.getRoot();

        View view = inflater.inflate(R.layout.test11_fragment5_listview, null);
//        ListView listView = view.findViewById(R.id.test11_fragment5_listview);
//        ArrayList<People> peoples = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            peoples.add(new People("https://img2.baidu.com/it/u=3323311628,2330835932&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=666"
//                    , "好人" + i, i * 2));
//        }
//
//        ListAdapter listAdapter = new ListAdapter(getActivity(), peoples, R.layout.test11_fragment5_listview_item, BR.people);
//        listView.setAdapter(listAdapter);


        return view;
    }
}
