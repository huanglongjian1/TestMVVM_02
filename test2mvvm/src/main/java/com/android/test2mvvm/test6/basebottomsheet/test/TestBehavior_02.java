package com.android.test2mvvm.test6.basebottomsheet.test;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.BottomBehaviorBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.basebottomsheet.MyDialogFragment_03;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class TestBehavior_02 extends BaseFragment<BottomBehaviorBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.bottom_behavior;
    }

    private BottomSheetBehavior<View> bottomSheet;

    @Override
    protected void initView() {
        bottomSheet = BottomSheetBehavior.from(getView().findViewById(R.id.bottom_sheet));
        bottomSheet.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //newState 有四个状态 ：
                //展开 BottomSheetBehavior.STATE_EXPANDED
                //收起 BottomSheetBehavior.STATE_COLLAPSED
                //拖动 BottomSheetBehavior.STATE_DRAGGING
                //下滑 BottomSheetBehavior.STATE_SETTLING
                Loge.e(String.valueOf(newState));
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，slideOffset为0-1 完全收起为0 完全展开为1
            }
        });
        binding.testbehavior02Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1(v);
            }
        });
    }

    private List<String> mList;

    private void initData1() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("item " + i);
        }
    }

    public void click1(View view) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        //创建recyclerView
        RecyclerView recyclerView = new RecyclerView(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(mList, getContext());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View item, int position) {
                Toast.makeText(getActivity(), "item " + position, Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(recyclerView);
        bottomSheetDialog.show();

        BottomSheetBehavior bottomSheetBehavior = bottomSheetDialog.getBehavior();
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Loge.e(String.valueOf(newState) + "============");
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        //  bottomSheetBehavior.setPeekHeight(2000);
    }

    @Override
    protected void initData() {
        initData1();
        binding.testbehavior02Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment_03 myDialogFragment_03 = new MyDialogFragment_03();
                myDialogFragment_03.show(getChildFragmentManager(), MyDialogFragment_03.class.getSimpleName());
                myDialogFragment_03.setTopOffset(1500);
            }
        });
    }
}
