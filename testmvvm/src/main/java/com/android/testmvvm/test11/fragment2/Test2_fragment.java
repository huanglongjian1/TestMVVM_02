package com.android.testmvvm.test11.fragment2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test11FragmentLayout2Binding;

public class Test2_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Test11FragmentLayout2Binding binding = DataBindingUtil.inflate(inflater, R.layout.test11_fragment_layout2, container, false);
        binding.setUser(new User("huang", "longjian"));
        binding.test11Fragment2Tv.setTextColor(Color.RED);

        return binding.getRoot();
    }
}
