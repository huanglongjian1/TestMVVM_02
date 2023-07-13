package com.android.test2mvvm.test5.fragment5.paging;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment5Binding;
import com.android.test2mvvm.test4.base.BaseFragment;

public class Paging_Fragment extends BaseFragment<Test5Fragment5Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment5;
    }

    @Override
    protected void initView() {
        UserPagedListAdapter userPagedListAdapter = new UserPagedListAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        userViewModel.getUserPagedList().observe(this, concerts -> {userPagedListAdapter.submitList(concerts);
            Log.e("TAG", "数据更新了");
        });


        binding.test5Fragment5Rv.setLayoutManager(linearLayoutManager);
        binding.test5Fragment5Rv.setAdapter(userPagedListAdapter);

    }

    UserViewModel userViewModel;

    @Override
    protected void initData() {
        userViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

    }
}
