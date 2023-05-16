package com.android.test2mvvm.test1.fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test1Fragment2Binding;

public class Test2_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Test1Fragment2Binding binding= DataBindingUtil.inflate(inflater, R.layout.test1_fragment2,container,false);
        ViewModel vm = new ViewModel();
        binding.setModel(vm);


        return binding.getRoot();
    }
}
