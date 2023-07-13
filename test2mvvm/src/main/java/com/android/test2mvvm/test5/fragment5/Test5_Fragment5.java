package com.android.test2mvvm.test5.fragment5;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment5Binding;
import com.android.test2mvvm.test4.base.BaseFragment;

public class Test5_Fragment5 extends BaseFragment<Test5Fragment5Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment5;
    }
    private RecyclerView mRecyclerView;
    private RecyclerAdapter adapter;
    @Override
    protected void initView() {
        mRecyclerView = root_view.findViewById(R.id.test5_fragment5_rv);
        adapter = new RecyclerAdapter();
        ConcertViewModel viewModel =
                new ViewModelProvider(getActivity()).get(ConcertViewModel.class);
        viewModel.getConvertList().observe(this, concerts -> adapter.submitList(concerts));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {

    }
}
