package com.android.test2mvvm.test5.fragment1;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment1Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.bean.UserViewModel;

import java.util.Random;

public class Test5_Fragment1 extends BaseFragment<Test5Fragment1Binding> {

    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment1;
    }

    @Override
    protected void initView() {
        MyAdapter myAdapter = new MyAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.test5Fragment1Recyclerview.setLayoutManager(linearLayoutManager);
        binding.setMyadapter(myAdapter);
        UserViewModel userViewModel = new UserViewModel();
        binding.setStumodel(userViewModel);

        binding.test5FragmentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Test5_DialogFragment test5_dialogFragment = new Test5_DialogFragment();
                //  test5_dialogFragment.show(getActivity().getSupportFragmentManager(), "dialog");
                String name = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
                //               Student student = new Student(name);
//                Loge.e(student.getStu_name());
//                myAdapter.getList().add(student);
//                myAdapter.notifyDataSetChanged();

                userViewModel.setName(name);

            }
        });
    }

    @Override
    protected void initData() {

    }
}
