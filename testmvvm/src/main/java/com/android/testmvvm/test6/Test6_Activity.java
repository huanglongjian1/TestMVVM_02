package com.android.testmvvm.test6;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test6ActivityLayoutBinding;
import com.android.testmvvm.uitl.Constants;
import com.android.testmvvm.uitl.Loge;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constants.TEST6_ACTIVITY)
public class Test6_Activity extends AppCompatActivity {
    Test6ActivityLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test6_activity_layout);
        Content con = new Content("Title", "SubTitle");
        binding.setCon(con);
        binding.toolbar.toolbar.setNavigationIcon(R.drawable.ic_launcher_background);
        setSupportActionBar(binding.toolbar.toolbar);
        final int[] i = {0, 0};
        binding.toolbar.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish();
                con.setSubTitle("我是新子标题" + i[0]++);
                con.setTitle("新題目" + i[1]++);
            }
        });
        initRecyclerView();
        binding.tvTest6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.add("F");
                binding.recycler.getAdapter().notifyDataSetChanged();
                Loge.e(datas.size() + "-");
            }
        });
    }

    List<String> datas = new ArrayList<>();

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(manager);
        binding.recycler.setHasFixedSize(true);
        datas.add("A");
        datas.add("B");
        datas.add("C");
        datas.add("D");
        datas.add("E");
        MyAdapter adapter = new MyAdapter(getApplicationContext(), datas);
        binding.recycler.setAdapter(adapter);

    }
}
