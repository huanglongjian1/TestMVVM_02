package com.android.test2mvvm.test5.fragment2;

import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment2Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.util.Loge;

import java.util.Random;

public class Test5_Fragment2 extends BaseFragment<Test5Fragment2Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment2;
    }

    @Override
    protected void initView() {
//        MyRecyclerView_Adapter myRecyclerView_adapter = new MyRecyclerView_Adapter(getContext());
//        myRecyclerView_adapter.getList().add("A");
//        myRecyclerView_adapter.getList().add("B");
//        myRecyclerView_adapter.getList().add("C");
        Fragment2_ViewModel fragment2_viewModel = new ViewModelProvider(getActivity()).get(Fragment2_ViewModel.class);
        binding.setModel(fragment2_viewModel);

        MyRecyclerView_Adapter myRecyclerView_adapter = new MyRecyclerView_Adapter(getContext(), fragment2_viewModel.observableArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.test5Fragment2Rv.setLayoutManager(linearLayoutManager);
        binding.setAdaper(myRecyclerView_adapter);
        myRecyclerView_adapter.setData_list(fragment2_viewModel.observableArrayList);
        fragment2_viewModel.observableArrayList.addOnListChangedCallback(ListFactory.getListChangedCallback(myRecyclerView_adapter));
        binding.test5Fragment2Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
                Loge.e(s);
                fragment2_viewModel.observableArrayList.add(s);
            }
        });
        binding.test5Fragment2Add5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObservableArrayList<String> stringObservableArrayList = new ObservableArrayList<>();
                for (int i = 0; i < 3; i++) {
                    String s = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
                    Loge.e(s);
                    stringObservableArrayList.add(s);
                }
                myRecyclerView_adapter.addAll(stringObservableArrayList);
            }
        });
        myRecyclerView_adapter.setOnItemClickListener(new ObservableArrayList_BaseRecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClickListener(Object data, int position) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
