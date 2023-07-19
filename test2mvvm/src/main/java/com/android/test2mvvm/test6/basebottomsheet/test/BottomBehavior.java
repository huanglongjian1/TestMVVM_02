package com.android.test2mvvm.test6.basebottomsheet.test;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.BottomSheetBehaviorBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test6.basebottomsheet.MyDialogSheetFragment_01;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Arrays;

public class BottomBehavior extends BaseFragment<BottomSheetBehaviorBinding> {
    public RecyclerView recyclerView;
    public BottomSheetBehavior behavior;
    public View blackView;
    private static final String shareStr[] = {
            "微信", "QQ", "空间", "微博", "GitHub",
    };

    @Override
    protected int getLayoutId() {
        return R.layout.bottom_sheet_behavior;
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        ObservableArrayList observableArrayList = new ObservableArrayList();
        observableArrayList.addAll(Arrays.asList(shareStr));
        SimpleStringRecyclerViewAdapter simpleStringRecyclerViewAdapter = new SimpleStringRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(simpleStringRecyclerViewAdapter);
        simpleStringRecyclerViewAdapter.setData_list(observableArrayList);

        behavior = BottomSheetBehavior.from(recyclerView);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED || newState == BottomSheetBehavior.STATE_HIDDEN) {
//                    blackView.setBackgroundColor(Color.TRANSPARENT);
                    blackView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
                blackView.setVisibility(View.VISIBLE);
                ViewCompat.setAlpha(blackView, slideOffset);
            }
        });
        getView().findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        blackView = getView().findViewById(R.id.blackview);
        blackView.setBackgroundColor(Color.parseColor("#60000000"));
        blackView.setVisibility(View.GONE);
        blackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        simpleStringRecyclerViewAdapter.setOnItemClickListener(new ObservableArrayList_BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Object data, int position) {
//                Loge.e(data + "-" + position);
//                // behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
//                View view = LayoutInflater.from(getContext()).inflate(R.layout.seekbar_fragment, null);
//                bottomSheetDialog.setContentView(view);
//                bottomSheetDialog.show();
//                BottomSheetBehavior bottomSheetBehavior = bottomSheetDialog.getBehavior();
//                bottomSheetBehavior.setHideable(false);
//                view.measure(0, 0);
//                bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());
                MyDialogSheetFragment_01 myDialogSheetFragment_01=new MyDialogSheetFragment_01();
                myDialogSheetFragment_01.show(getChildFragmentManager(),MyDialogSheetFragment_01.class.getSimpleName());

                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                Loge.e(blackView.getWindowToken() + "--" + recyclerView.getWindowToken());

            }
        });
    }

    @Override
    protected void initData() {

    }
}
