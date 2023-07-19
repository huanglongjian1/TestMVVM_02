package com.android.test2mvvm.test6.basebottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentABinding;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test6.basebottomsheet.test.SimpleStringRecyclerViewAdapter;
import com.android.test2mvvm.util.Loge;

public class MyDialogSheetFragment_01 extends BaseFullBottomSheetFragment {
    ResultFragmentABinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.result_fragment_a, null, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ObservableArrayList observableArrayList = new ObservableArrayList();
        for (int i = 0; i < 10; i++) {
            observableArrayList.add("aaaa" + i);
        }
        SimpleStringRecyclerViewAdapter simpleStringRecyclerViewAdapter = new SimpleStringRecyclerViewAdapter(getContext());
        simpleStringRecyclerViewAdapter.setData_list(observableArrayList);
        binding.resultFragmentARecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.resultFragmentARecyclerview.setAdapter(simpleStringRecyclerViewAdapter);
        simpleStringRecyclerViewAdapter.setOnItemClickListener(new ObservableArrayList_BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Object data, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(MyDialogSheetFragment_02.class.getSimpleName(), (String) data);
                MyDialogSheetFragment_02 myDialogSheetFragment_02 = MyDialogSheetFragment_02.newInstance(bundle);
                myDialogSheetFragment_02.show(getChildFragmentManager(), MyDialogSheetFragment_02.class.getSimpleName());
            }
        });

        View view1=getDialog().getWindow().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        View view2= (View) getView().getParent();
        Loge.e(view1.toString()+"----"+view2.toString());
        setTopOffset(1000);
    }
}
