package com.android.test2mvvm.test6.fragments.result.fragmenta;

import android.os.Bundle;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentABinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test6.MessageListener;
import com.android.test2mvvm.test6.fragments.result.ViewModel_AB;
import com.android.test2mvvm.util.Loge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Result_FragmentA extends BaseFragment<ResultFragmentABinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.result_fragment_a;
    }

    @Override
    protected void initView() {
        binding.resultFragmentARecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.resultFragmentARecyclerview.setAdapter(new StringAdapter(getContext()));
        StringAdapter stringAdapter = (StringAdapter) binding.resultFragmentARecyclerview.getAdapter();
        ObservableArrayList<String> observableArrayList = new ObservableArrayList<>();
        observableArrayList.addAll(stringList);
        stringAdapter.setData_list(observableArrayList);

        stringAdapter.setOnClickListener(new StringAdapter.OnClickListener() {
            @Override
            public void onClick(Object data, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("data", (String) data);
                Loge.e("被点击了" + position);
                getParentFragmentManager().setFragmentResult(Result_FragmentA.class.getSimpleName(), bundle);

                MessageListener messageListener = (MessageListener) getActivity();
                messageListener.getMessageFromFragment(Result_FragmentA.this, data);
            }
        });
    }

    List<String> stringList = new ArrayList<>();

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        for (int i = 'A'; i <= 'Z'; i++) {
            stringList.add(String.valueOf((char) i));
        }
        initViewModel();
    }

    private void initViewModel() {
        ViewModel_AB viewModel_ab=new ViewModelProvider(getActivity()).get(ViewModel_AB.class);
        binding.setDataab(viewModel_ab);
        binding.setLifecycleOwner(getActivity());
    }

    @Subscribe()
    public void onReceiveMsg(String msg) {
        Loge.e(msg + ":" + Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
