package com.android.test2mvvm.test5;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.Observable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.work.WorkManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.R;
import com.android.test2mvvm.base.BaseActivity;
import com.android.test2mvvm.databinding.Test5ActivityBinding;
import com.android.test2mvvm.test5.bean.Stu_ViewModel;
import com.android.test2mvvm.test5.bean.Teacher;
import com.android.test2mvvm.test5.bean.User;
import com.android.test2mvvm.test5.fragment6.TestAppWidgetRemoteViewsService;
import com.android.test2mvvm.test5.fragment7.testroom.Test_Fragment;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.ui.AnimationUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.Random;

@Route(path = Constants.TEST5_ACTIVITY)
public class Test5_Activity extends BaseActivity<Test5_Activity_Model, Test5ActivityBinding> {

    @Override
    protected int getContentViewId() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Loge.e("llllllllllllll");
            String s = bundle.getString("key");
            if (!TextUtils.isEmpty(s) && s.equals("取消worker")) {
                WorkManager.getInstance(this).cancelAllWork();
                Loge.e("取消所有worker");
                stopService(new Intent(this, TestAppWidgetRemoteViewsService.class));
            }
        }
        Loge.e(getPackageName());
        return R.layout.test5_activity;
    }

    @Override
    protected void processLogic() {
        initNav(1);

        initTablayout();
        getSupportFragmentManager().beginTransaction().add(R.id.test5_fragment, new Test_Fragment()).commit();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //  actionBar.setBackgroundDrawable(getDrawable(R.drawable.ic_loading));
        NavigationUI.setupActionBarWithNavController(this, Navigation.findNavController(this, R.id.nav_host_fragment_activity_test5));

        binding.test5ChangeFragmentNavFragment7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initNav(1);
            }
        });
        binding.test5ChangeFragmentNavMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initNav(2);
            }
        });
    }

    private void initTablayout() {
        binding.test5ActivityTablayout.addTab(binding.test5ActivityTablayout.newTab().setText("fragment7"));
        binding.test5ActivityTablayout.addTab(binding.test5ActivityTablayout.newTab().setText("mobile"));
        binding.test5ActivityTablayout.addTab(binding.test5ActivityTablayout.newTab().setText("dialog"));
        binding.test5ActivityTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()){
                    case "fragment7":
                        initNav(1);
                        break;
                    case "mobile":
                        initNav(2);
                        break;
                    case "dialog":
                        initNav(3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment_activity_test5).navigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();  //实例化一个MenuInflater对象
        inflater.inflate(R.menu.toolbar, menu);       //解析菜单文件
        return super.onCreateOptionsMenu(menu);
    }

    private void initNav(int type) {
        NavController controller = Navigation.findNavController(this, R.id.nav_host_fragment_activity_test5); //在Activity里获取NavController实例
        if (type == 1) {
            controller.setGraph(R.navigation.fragment7);  //设置xml文件
            return;
        }
        if (type == 2) {
            Bundle bundle = new Bundle();
            bundle.putString("name", "demo");
            controller.setGraph(R.navigation.mobile_navigation); //设置xml文件的并传入数据，这个数据可以在启动的Fragment里获取到
            return;
        }
        if (type == 3) {
            controller.setGraph(R.navigation.navigation_dialog); //设置xml文件的并传入数据，这个数据可以在启动的Fragment里获取到
            return;
        }
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Loge.e("back");
                break;
            case android.R.id.home:
                //  Navigation.findNavController(this, R.id.nav_host_fragment_activity_test5).navigateUp();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void registerViewModel() {
        binding.test5ActivityTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationUtil.hideAlphaAnimation(binding.test5ActivityTv, 5000);
            }
        });
//        setSupportActionBar(binding.test5Toolbar);
//        binding.test5Toolbar.setNavigationIcon(R.drawable.ic_loading);
//        binding.test5Toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }

    @Override
    protected void initEvent() {
        User user = new User();
        binding.setUser(user);
        binding.test5ActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
                user.setAge(age);
            }
        });
        user.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                User user1 = (User) sender;
                Loge.e(user1.getAge());
            }
        });
    }

    @Override
    protected void initData() {
        Stu_ViewModel stu_viewModel = new Stu_ViewModel();
        binding.setStumodel(stu_viewModel);
        binding.test5ActivityStudentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
                stu_viewModel.setUserNameA(age);
                Loge.e(stu_viewModel.getUserNameA());
            }
        });
        Teacher teacher = new Teacher();
        binding.setTeacher(teacher);

        binding.test5Fragment1TeacherTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = new Random().nextInt(Integer.MAX_VALUE);
                teacher.observableArrayList.add(String.valueOf(age));
            }
        });
        binding.test5Fragment1TeacherMapTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = String.valueOf(new Random().nextInt(10000));
                String value = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
                teacher.observableArrayMap.put(key, value);
            }
        });
        Navigation.findNavController(this, R.id.nav_host_fragment_activity_test5).addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                Loge.e(navDestination.getLabel().toString());
            }
        });
    }
}
