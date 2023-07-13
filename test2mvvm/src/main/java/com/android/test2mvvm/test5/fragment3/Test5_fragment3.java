package com.android.test2mvvm.test5.fragment3;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment3Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test5.fragment2.ListFactory;
import com.android.test2mvvm.util.Loge;

public class Test5_fragment3 extends BaseFragment<Test5Fragment3Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment3;
    }

    @Override
    protected void initView() {
        Fragment3_Adapter fragment3_adapter = new Fragment3_Adapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.test5Fragment3Rv.setLayoutManager(linearLayoutManager);
        binding.setAdapter(fragment3_adapter);
        fragment3_adapter.setData_list(fragment3_viewModel.observableArrayList);
        fragment3_viewModel.observableArrayList.addOnListChangedCallback(ListFactory.getListChangedCallback(fragment3_adapter));


        binding.test5Fragment3Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment3_adapter.setDiffer_Data(new Goods("dianzan", "desc"));
                //Loge.e(fragment3_adapter.getmDiffer().getCurrentList().toString() + "---mdiffer");

            }
        });
        fragment3_adapter.setOnFootClickListener(new ObservableArrayList_BaseRecyclerViewAdapter.OnFootClickListener() {
            @Override
            public void onFootClickListener() {
                Loge.e("点击加载更多");
            }
        });
    }


    Fragment3_ViewModel fragment3_viewModel;

    @Override
    protected void initData() {
        fragment3_viewModel = new ViewModelProvider(getActivity()).get(Fragment3_ViewModel.class);
        binding.setViewmodel(fragment3_viewModel);


    }
}
