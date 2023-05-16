package com.android.testmvvm.test11.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test11FragmentLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class Test_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Test11FragmentLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.test11_fragment_layout, container, false);
        MainVM mainVM = new ViewModelProvider(getActivity()).get(MainVM.class);
        binding.setVm(mainVM);
        binding.setLifecycleOwner(this);
        mainVM.setBinding(binding, this);


        return binding.getRoot();
    }
}
