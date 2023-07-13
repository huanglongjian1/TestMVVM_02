package com.android.test2mvvm.test5.fragment5.book;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment5Binding;
import com.android.test2mvvm.test4.base.BaseFragment;

public class Book_Fragment extends BaseFragment<Test5Fragment5Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment5;
    }

    @Override
    protected void initView() {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.test5Fragment5Rv.setLayoutManager(linearLayoutManager);
        binding.test5Fragment5Rv.setAdapter(recyclerAdapter);
        bookViewModel.getConvertList().observe(this, new Observer<PagedList<Book>>() {
            @Override
            public void onChanged(PagedList<Book> books) {
                recyclerAdapter.submitList(books);
            }
        });
    }

    BookViewModel bookViewModel;

    @Override
    protected void initData() {
        bookViewModel = new ViewModelProvider(getActivity()).get(BookViewModel.class);
    }
}
