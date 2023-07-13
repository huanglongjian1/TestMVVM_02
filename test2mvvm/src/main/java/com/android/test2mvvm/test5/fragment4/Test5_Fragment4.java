package com.android.test2mvvm.test5.fragment4;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment4Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.fragment2.adapter.BaseAdapter;
import com.android.test2mvvm.test5.fragment2.adapter.Bean;
import com.android.test2mvvm.test5.fragment3.Loading;
import com.android.test2mvvm.test5.fragment3.headandtail_rv.HeaderViewRecyclerAdapter;
import com.android.test2mvvm.test5.fragment4.bean.Person;
import com.android.test2mvvm.test5.fragment4.dao.Person_Dao;
import com.android.test2mvvm.test5.fragment4.dao.Person_Database;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Test5_Fragment4 extends BaseFragment<Test5Fragment4Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment4;
    }

    Person_Dao person_dao;

    @Override
    protected void initView() {
        binding.test5Fragment4Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.timer(0, TimeUnit.MILLISECONDS).observeOn(Schedulers.io())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Throwable {
                                Loge.e(Thread.currentThread().getName());
                                person_dao.insert(new Person("A", "123"));
                            }
                        });
            }
        });

        binding.test5ChangeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseAdapter baseAdapter1 = new BaseAdapter() {
                    @Override
                    protected int getRecyclerViewItemID() {
                        return R.layout.itemview;
                    }

                    @Override
                    protected void onBindVH(RecyclerView.ViewHolder holder, int position) {
                        Bean<String> bean = data_list.get(position);
                        TextView textView = holder.itemView.findViewById(R.id.item_tv);
                        textView.setText(bean.data);
                    }
                };
                List<Bean> beans1 = new ArrayList<>();
                beans1.add(new Bean(Bean.HEAD, "head_test"));
                for (int i = 1; i < 5; i++) {
                    Bean<String> bean = new Bean<>();
                    bean.data = "item_test" + i;
                    bean.type = Bean.ITEM;
                    beans1.add(bean);
                }
                beans1.add(new Bean(Bean.FOOT, "foot_test"));
                baseAdapter1.setData_list(beans1);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                binding.wrapRv.setLayoutManager(linearLayoutManager);
                binding.wrapRv.setAdapter(baseAdapter1);
                baseAdapter1.setLoading(new Loading() {
                    @Override
                    public void onLoadMore() {
                        Loge.e("测试foot");
                    }

                    @Override
                    public void refresh() {
                        Loge.e("测试head");
                    }
                });

            }
        });

    }

    @Override
    protected void initData() {
        Fragment4_ViewModel fragment4_viewModel = new ViewModelProvider(getActivity()).get(Fragment4_ViewModel.class);
        binding.setViewmodel(fragment4_viewModel);
        person_dao = Person_Database.getDatabase(getContext()).person_dao();
    }
}
