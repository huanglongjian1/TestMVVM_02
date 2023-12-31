package com.android.test2mvvm.test6.fragments.dialogfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;

public class MyDialogFragment extends DialogFragment {
    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.mydialog_fragment, container, false);
        }
        return root;
    }
}
