package com.android.testmvvm.test1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test1FragmentLayout1Binding;

public class Test1_Fragment extends Fragment {
    Test1FragmentLayout1Binding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test1_fragment_layout1, container, false);
        View view = binding.getRoot();
        binding.setUser(new User("zhangsan", "heheh"));

        return view;
    }
}
