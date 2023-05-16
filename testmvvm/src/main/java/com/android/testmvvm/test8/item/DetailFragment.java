package com.android.testmvvm.test8.item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.testmvvm.R;
import com.android.testmvvm.uitl.Loge;

public class DetailFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedViewModel model = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        model.getSelected().observe(this, item -> {
            // Update the UI.
            Loge.e("来自master的消息"+item.getName()+"-"+item.getAge());
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        return view;
    }
}