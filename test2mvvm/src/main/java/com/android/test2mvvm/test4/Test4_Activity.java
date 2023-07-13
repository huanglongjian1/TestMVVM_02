package com.android.test2mvvm.test4;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.R;
import com.android.test2mvvm.base.BaseActivity;
import com.android.test2mvvm.databinding.Test4ActivityBinding;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


@Route(path = Constants.TEST4_ACTIVITY)
public class Test4_Activity extends BaseActivity<Test4_ViewModel, Test4ActivityBinding> {

    @Override
    protected int getContentViewId() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) Loge.e(bundle.getString("key", "没有消息"));
        return R.layout.test4_activity;
    }

    @Override
    protected void processLogic() {
        binding.test4ActivityTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(Test4_Activity.class.getSimpleName(), "来自test4的消息");
                intent.putExtras(bundle);
                setResult(1001, intent);
                finish();
            }
        });
    }

    @Override
    protected void registerViewModel() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initEvent() {
//        binding.test4BottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.menu_home:
//
//                        break;
//                    case R.id.menu_service:
//
//                        break;
//                    case R.id.menu_rich:
//
//                        break;
//                    case R.id.menu_news:
//
//                        break;
//                    case R.id.menu_mine:
//
//                        break;
//                }
//                return true;
//            }
//        });

        NavController controller = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(binding.test4BottomNavigationView, controller);

    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true, priority = 1)
    public void onEventMainThread(String msg) {
        Loge.e(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
