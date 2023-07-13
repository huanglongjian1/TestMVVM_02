package com.android.test2mvvm.base;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class Base_LazyFragment extends Fragment {


    private static final String TAG = "LazyFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,getName() + " onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,getName() + " onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG,getName() + " onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,getName() + " onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,getName() + " loadData");
        loadData();
    }

    protected abstract void loadData();

    public abstract String getName();

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,getName() + " onDestroy");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,getName() + " stopData");
        stopData();
    }

    protected abstract void stopData();

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,getName() + " onStop");
    }
}

