package com.android.test2mvvm.test6.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;

public class MyDialogFragment extends DialogFragment {
    public static final Integer DIALOG_TYPE = 1;
    public static final Integer DIALOG_VIEW = 2;
    private int type;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getType() == DIALOG_TYPE) {
            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setIcon(R.drawable.ic_launcher_background)
                    .setMessage("今晚打老虎").create();
            return alertDialog;
        } else {
            return super.onCreateDialog(savedInstanceState);
        }
    }

    public MyDialogFragment() {
    }

    public MyDialogFragment(int contentLayoutId, int type) {
        super(contentLayoutId);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Loge.e(getDialog().toString() + "-----------");
        View view = inflater.inflate(R.layout.seekbar_fragment, null);
        TextView textView = view.findViewById(R.id.seekbar_fragment_tv_test6);
        textView.setText("今晚打老虎必胜客");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SeekBar seekBar = getView().findViewById(R.id.seekbar_test6);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Loge.e(String.valueOf(progress) + ":进度条");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
