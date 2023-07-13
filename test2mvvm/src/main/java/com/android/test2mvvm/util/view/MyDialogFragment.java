package com.android.test2mvvm.util.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;

public class MyDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_sload_layout, null);
        TextView textView = view.findViewById(R.id.tv_loading);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(savedInstanceState).show();
            }
        });
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("系统弹窗")
                .setMessage("信息")
                .setIcon(R.drawable.ic_launcher_background)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NavController navController=Navigation.findNavController(getView());
                        navController.navigateUp();
                        Loge.e(navController.getCurrentDestination().getLabel().toString()+"---");
                        Loge.e(navController.getCurrentDestination().getDisplayName()+"---");
                        if (navController.getCurrentDestination().getId()==R.id.main_fragment){
                            Loge.e("准备转移到login");
                            navController.navigate(R.id.action_main_to_login);
                        }
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "确认", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        return dialog;
    }
}
