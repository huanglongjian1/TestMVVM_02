package com.android.test2mvvm.test5.fragment6.aac;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.databinding.Test5Fragment5Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.fragment3.Loading;
import com.android.test2mvvm.test5.fragment4.bean.Person;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;

public class AAC_Fragment extends BaseFragment<Test5Fragment5Binding> {
    private Context mActivity;
    private NewsViewModel newsViewModel;
    private DataAdapter dataAdapter;
    private List<NewsDataVo> mData;

    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment5;
    }

    @Override
    protected void initView() {
        AAC_ViewModel aac_viewModel = new ViewModelProvider(getActivity()).get(AAC_ViewModel.class);
        aac_viewModel.getStringMutableLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null && !TextUtils.isEmpty(s)) {
                    Loge.e(s);
                }
            }
        });
        aac_viewModel.getIntegerLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer != null) {
                    Loge.e(integer.toString());
                }
            }
        });
        aac_viewModel.getMutableLiveData().observe(this, new Observer<Person>() {
            @Override
            public void onChanged(Person person) {

            }
        });


        saveStateHandle_viewModel = new ViewModelProvider(getActivity()).get(SaveStateHandle_ViewModel.class);
        saveStateHandle_viewModel.getNum().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer != null) {
                    Loge.e(integer.toString());
                }
            }
        });
        binding.setLifecycleOwner(this);
    }

    SaveStateHandle_ViewModel saveStateHandle_viewModel;

    @Override
    protected void initData() {
        binding.test5Fragment5Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   newsViewModel.httpGetData();

                saveStateHandle_viewModel.addNum();
            }
        });
        mActivity = getActivity();
        // mData = new ArrayList<>();
        mData = new ObservableArrayList<>();
        dataAdapter = new DataAdapter(mData);
        binding.test5Fragment5Rv.setHasFixedSize(true);
        binding.test5Fragment5Rv.setLayoutManager(new LinearLayoutManager(mActivity));
        //  binding.test5Fragment5Rv.setAdapter(dataAdapter);

        Data01_Adapter data01_adapter = new Data01_Adapter(getActivity());
        data01_adapter.setData_list((ObservableArrayList<NewsDataVo>) mData);
        binding.test5Fragment5Rv.setAdapter(data01_adapter);

        addObserver();
    }

    private void addObserver() {
        AccLifecycleObserver observer = new AccLifecycleObserver(mActivity);
        getLifecycle().addObserver(observer);


        getLifecycle().addObserver(new AccLifecycleObserver_01());
//        ViewModelProvider.AndroidViewModelFactory instance = ViewModelProvider.AndroidViewModelFactory.getInstance((Application) Test2_App.getInstance());
//        newsViewModel = instance.create(NewsViewModel.class);
        newsViewModel = new ViewModelProvider(getActivity()).get(NewsViewModel.class);

        newsViewModel.getSwitchDataMap().observe(getActivity(), new Observer<NewsDataVo>() {
            @Override
            public void onChanged(NewsDataVo newsDataVo) {
                assert newsDataVo != null;
                mData.add(newsDataVo);

                dataAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        saveStateHandle_viewModel.save();
    }
}
