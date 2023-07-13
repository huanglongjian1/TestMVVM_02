package com.android.test2mvvm.test4.fragment2;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.adapter.BaseRecyclerViewAdapter;
import com.android.test2mvvm.databinding.Test4Fragment2Binding;
import com.android.test2mvvm.test4.Test;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.util.Loge;

import java.util.Random;

public class Test4_Fragment2 extends BaseFragment<Test4Fragment2Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test4_fragment2;
    }

    @Override
    protected void initView() {
        Fragment2_Adapter adapter = new Fragment2_Adapter(getContext());
        for (int i = 0; i < 50; i++) {
            User user = new User("hlj" + i, new Random().nextInt(Integer.MAX_VALUE));
            adapter.getList().add(user);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.test4Fragment2RecyclerView.setLayoutManager(linearLayoutManager);
        binding.test4Fragment2RecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        binding.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Object data, int position) {
                User user= (User) data;
                Loge.e(user.name+user.password+position);
            }
        });


    }

    @Override
    protected void initData() {

    }
}
