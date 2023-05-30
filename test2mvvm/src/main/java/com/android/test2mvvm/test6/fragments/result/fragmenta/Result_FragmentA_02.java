package com.android.test2mvvm.test6.fragments.result.fragmenta;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentABinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.fragments.result.ViewModel_AB;

public class Result_FragmentA_02 extends BaseFragment<ResultFragmentABinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.result_fragment_a;
    }

    @Override
    protected void initView() {
        StringAdapter stringAdapter = new StringAdapter(getContext());
        ObservableArrayList<String> stringObservableArrayList = new ObservableArrayList<>();
        for (int i = 'A'; i < 'A' + 26; i++) {
            stringObservableArrayList.add(String.valueOf((char) i));
        }
        stringAdapter.setData_list(stringObservableArrayList);
        binding.resultFragmentARecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.resultFragmentARecyclerview.setAdapter(stringAdapter);
        stringAdapter.setOnClickListener(new StringAdapter.OnClickListener() {
            @Override
            public void onClick(Object data, int position) {
                viewModel_ab.stringMutableLiveData.setValue((String) data);
            }
        });
    }

    ViewModel_AB viewModel_ab;

    @Override
    protected void initData() {
        viewModel_ab = new ViewModelProvider(getActivity()).get(ViewModel_AB.class);
        binding.setDataab(viewModel_ab);
        binding.setLifecycleOwner(this);
    }
}
