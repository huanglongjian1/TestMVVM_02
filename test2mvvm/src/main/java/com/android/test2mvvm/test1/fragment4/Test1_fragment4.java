package com.android.test2mvvm.test1.fragment4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test1Fragment4Binding;

public class Test1_fragment4 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Test1Fragment4Binding recyclerBindingAdapterBinding = DataBindingUtil.inflate(inflater, R.layout.test1_fragment4, container, false);
        recyclerBindingAdapterBinding.test1Fragment4RecyclerView.setHasFixedSize(true);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(new RecyclerViewViewModel().getBooks());
        recyclerBindingAdapterBinding.test1Fragment4RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerBindingAdapterBinding.test1Fragment4RecyclerView.setAdapter(adapter);


        return recyclerBindingAdapterBinding.getRoot();
    }
}
