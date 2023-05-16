package com.android.testmvvm.test11;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test11ActivityLayoutBinding;
import com.android.testmvvm.test1.Test1_Fragment;
import com.android.testmvvm.test11.fragment.Test_Fragment;
import com.android.testmvvm.test11.fragment2.Test2_fragment;
import com.android.testmvvm.test11.fragment4.Test11_Fragment4;
import com.android.testmvvm.test11.fragment5.Test5_Fragment;
import com.android.testmvvm.uitl.Constants;
import com.android.testmvvm.uitl.Loge;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
@Route(path = Constants.TEST11_ACTIVITY)
public class Test11_Activity extends AppCompatActivity {
    private static RecyclerView recyclerView;
    jvvm_recycleAdapter adapter;
    @Inject
    com.android.testmvvm.test11.fragment2.User user1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Test11ActivityLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.test11_activity_layout);
        User user = new User("Test", "User");
        binding.setUser(user);

        recyclerView = binding.recyclerview;
        adapter = new jvvm_recycleAdapter(this, initData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        binding.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("大吉大利，准备吃鸡");
                getSupportFragmentManager().beginTransaction().replace(R.id.test11_fragment, new Test1_Fragment()).commit();
                Loge.e(user1.firstName + "\n" + user1.lastName);

            }
        });
        adapter.getList().add(adapter.getList().size(), new TextMessageBean("好消息").setImagurl("https://www.baidu.com/img/bd_logo1.png"));
        adapter.notifyDataSetChanged();
    }

    public List<BaseMessage> initData() {
        List<BaseMessage> list = new ArrayList<>();
        list.add(new TextMessageBean("新消息一"));
        list.add(new TextMessageBean("新消息二"));
        list.add(new ButtonMessageBean("今晚"));
        list.add(new TextMessageBean("新消息三"));
        list.add(new TextMessageBean("新消息四"));
        list.add(new TextMessageBean("新消息五"));
        list.add(new TextMessageBean("新消息六"));
        list.add(new ButtonMessageBean("ppppppppppp"));
        list.add(new ButtonMessageBean("xxxxxxxxxxx"));

        return list;
    }
}
