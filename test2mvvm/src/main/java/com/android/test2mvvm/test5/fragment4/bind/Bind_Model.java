package com.android.test2mvvm.test5.fragment4.bind;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test5.fragment4.adapter.Person_RV_Adapter;
import com.android.test2mvvm.test5.fragment4.bean.Person;
import com.android.test2mvvm.util.Loge;

public class Bind_Model {
    @BindingAdapter("bind")
    public static void bind_model(RecyclerView recyclerView, ObservableArrayList<Person> personObservableArrayList) {
        Loge.e(personObservableArrayList.toString());
        Person_RV_Adapter person_rv_adapter = new Person_RV_Adapter(recyclerView.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(person_rv_adapter);
        person_rv_adapter.setData_list(personObservableArrayList);
        person_rv_adapter.setOnItemClickListener(new ObservableArrayList_BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Object data, int position) {
                Loge.e(data.toString()+"----"+position);
            }
        });
    }
}
