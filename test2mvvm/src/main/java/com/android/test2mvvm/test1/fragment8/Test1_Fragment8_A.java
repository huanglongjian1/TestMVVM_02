package com.android.test2mvvm.test1.fragment8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test1Fragment8Binding;
import com.android.test2mvvm.test1.fragment5.User;
import com.android.test2mvvm.test1.fragment8.adapter.SimpleDataBindingAdapter;
import com.android.test2mvvm.util.Loge;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;

public class Test1_Fragment8_A extends Fragment {
    Test1Fragment8Binding binding;
    UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test1_fragment8, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        userViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);
        binding.setViewmodel(userViewModel);
        binding.setFragment(this);
        initRecyclerview();
        addObserver();
    }

    private void initRecyclerview() {
        SimpleDataBindingAdapter simpleDataBindingAdapter = new SimpleDataBindingAdapter(getActivity());
        simpleDataBindingAdapter.getItems().add(new User("A", "B", "C", "D"));
        simpleDataBindingAdapter.getItems().add(new User("A", "B", "C", "D"));
        simpleDataBindingAdapter.getItems().add(new User("A", "B", "C", "D"));
        simpleDataBindingAdapter.getItems().add(new User("A", "B", "C", "D"));
        simpleDataBindingAdapter.getItems().add(new User("A", "B", "C", "D"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.test1Fragment8Recyclerview.setLayoutManager(linearLayoutManager);
        binding.test1Fragment8Recyclerview.setAdapter(simpleDataBindingAdapter);
    }

    public void addObserver() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull ObservableEmitter<String> emitter) throws Throwable {
                userViewModel.content.observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        emitter.onNext(s);
                    }
                });

            }
        });
        observable.throttleLast(1, TimeUnit.SECONDS).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Loge.e("发射的数据" + s);
            }
        });
        userViewModel.showLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.test1Fragment8Showloading.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            }
        });

    }

    public void setData(View view) {
        Loge.e("setdata");
        userViewModel.setData();
    }

    public void getData(View view) {
        Loge.e("getdata");
        userViewModel.getData();
    }

    public void add_data(View view) {
        User user = new User("章", "赵", "何", "林");
        SimpleDataBindingAdapter simpleDataBindingAdapter = (SimpleDataBindingAdapter) binding.test1Fragment8Recyclerview.getAdapter();
        simpleDataBindingAdapter.getItems().add(user);
        simpleDataBindingAdapter.notifyDataSetChanged();
    }

}
