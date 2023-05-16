package com.android.test2mvvm.test1.fragment6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ActivityNotebookBinding;

public class Test1_fragment6 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivityNotebookBinding binding = DataBindingUtil.inflate(inflater, R.layout.activity_notebook, container, false);

        return binding.getRoot();
    }
}
