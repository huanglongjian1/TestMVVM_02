package com.android.test2mvvm.test6;

import static android.content.ContentValues.TAG;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.R;
import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.base.BaseActivity;
import com.android.test2mvvm.databinding.Test6ActivityBinding;
import com.android.test2mvvm.test6.adapter.MyViewPager2Adapter;
import com.android.test2mvvm.test6.basebottomsheet.test.BottomBehavior;
import com.android.test2mvvm.test6.basebottomsheet.test.TestBehavior;
import com.android.test2mvvm.test6.basebottomsheet.test.TestBehavior_02;
import com.android.test2mvvm.test6.fragments.Dialog_Fragment;
import com.android.test2mvvm.test6.fragments.Fragment07;
import com.android.test2mvvm.test6.fragments.Mobile_Fragment;
import com.android.test2mvvm.test6.fragments.newinstance.BlankFragment;
import com.android.test2mvvm.test6.fragments.newinstance.ItemFragment;
import com.android.test2mvvm.test6.fragments.newinstance.test02.FullscreenFragment;
import com.android.test2mvvm.test6.fragments.newinstance.test04.SettingsFragment;
import com.android.test2mvvm.test6.fragments.onbackpressed.OnBack_Fragment;
import com.android.test2mvvm.test6.fragments.result.Result_Fragment;
import com.android.test2mvvm.test6.fragments.result.Result_Fragment_02;
import com.android.test2mvvm.test6.fragments.result.fragmentb.Result_Fragment_03;
import com.android.test2mvvm.test6.fragments.seekbar_fragment.SeekBar_Fragment;
import com.android.test2mvvm.test6.fragments.test_fragment.Test_Fragment;
import com.android.test2mvvm.test6.test_fragment.Test_Fragment_01;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.onbackpressed.BackHandlerHelper;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

@Route(path = Constants.TEST6_ACTIVITY)
public class Test6_Activity extends BaseActivity<Test6_ViewModel, Test6ActivityBinding> implements MessageListener<String> {
    @Override
    protected int getContentViewId() {
        return R.layout.test6_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Loge.e(Test2_App.string+"\\\\\\\\\\\\\\\\");
        Loge.e(String.valueOf(android.os.Process.myPid()) + ":test6");
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(mFragmentLifecycleCallbacks, true);
     //   getCurrentProcessNameByActivityManager(this);
    }

    public static String getCurrentProcessNameByActivityManager(@NonNull Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppList = am.getRunningAppProcesses();
            if (runningAppList != null) {
                for (ActivityManager.RunningAppProcessInfo processInfo : runningAppList) {
                    if (processInfo.pid == pid) {
                        Loge.e(processInfo.processName + ":test6");
                        return processInfo.processName;
                    }
                }
            }
        }
        return null;
    }

    @Override
    protected void processLogic() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(Test_Fragment_01.newInstance(null));
        fragmentList.add(Test_Fragment.newInstance("测试fragmentAAA"));
        fragmentList.add(new TestBehavior_02());
        fragmentList.add(new BottomBehavior());
        Bundle bundle = new Bundle();
        bundle.putString("new", "新名字");
        fragmentList.add(Result_Fragment_03.newInstance(bundle));
        fragmentList.add(new TestBehavior());
        fragmentList.add(new Mobile_Fragment());
        fragmentList.add(new Dialog_Fragment());
        fragmentList.add(new Fragment07());
        fragmentList.add(new SeekBar_Fragment());
        fragmentList.add(new Result_Fragment());
        fragmentList.add(new Result_Fragment_02());
        fragmentList.add(BlankFragment.newInstance("blank", "fragment"));
        fragmentList.add(ItemFragment.newInstance(20));
        fragmentList.add(new FullscreenFragment());
        fragmentList.add(new SettingsFragment());
        fragmentList.add(new OnBack_Fragment());


        MyViewPager2Adapter myViewPager2Adapter = new MyViewPager2Adapter(this, fragmentList);
        binding.viewpager2ActivityTest6.setAdapter(myViewPager2Adapter);
        binding.viewpager2ActivityTest6.setOffscreenPageLimit(3);

        new TabLayoutMediator(binding.tablayoutActivityTest6, binding.viewpager2ActivityTest6, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(fragmentList.get(position).getClass().getSimpleName().toLowerCase());
            }
        }).attach();
        binding.viewpager2ActivityTest6.setOffscreenPageLimit(1);
        binding.test6ActivityTv.setOnClickListener(new View.OnClickListener() {
            int anInt = 0;

            @Override
            public void onClick(View v) {
                SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(Test6_Activity.this);
                String my_edittext_preference = mySharedPreferences.getString("signature", "没有取到数据");
                Loge.e(my_edittext_preference);

//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.test6_activity_fragment, fragmentList.get(anInt))
//                        // .addToBackStack(fragmentList.get(anInt).getClass().getSimpleName())
//                        .addToBackStack(null)
//                        .commit();
//                anInt++;
//                if (anInt > fragmentList.size() - 1) {
//                    anInt = 0;
//                }
            }
        });
//
//        binding.viewpager2ActivityTest6.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                Loge.e("====" + position);
//                Fragment fragment = fragmentList.get(position);
//
//
//                if (fragment instanceof Dialog_Fragment) {
//                    Dialog_Fragment dialog_fragment = (Dialog_Fragment) fragment;
//                    dialog_fragment.setNavController(new GetNavController() {
//                        @Override
//                        public void onCreateNavController(NavController navController) {
//                            Loge.e("Dialog_Fragment");
//                            navController1 = navController;
//                            Loge.e(navController.toString() + "==================");
//                            NavigationUI.setupActionBarWithNavController(Test6_Activity.this, navController1);
//
//                            View navHostFragment = fragment.getView().findViewById(R.id.nav_host_fragment_fragment_test6_dialog);
//                            NavController navController2 = Navigation.findNavController(navHostFragment);
//                            Loge.e(navController2.toString());
//                        }
//                    });
//                }
//                if (fragment instanceof Fragment07) {
//                    Fragment07 fragment07 = (Fragment07) fragment;
//                    fragment07.setNavController(new GetNavController() {
//                        @Override
//                        public void onCreateNavController(NavController navController) {
//                            Loge.e("Fragment07");
//                            navController1 = navController;
//                            NavigationUI.setupActionBarWithNavController(Test6_Activity.this, navController1);
//                        }
//                    });
//                }
//                if (fragment instanceof Mobile_Fragment) {
//                    Mobile_Fragment mobile_fragment = (Mobile_Fragment) fragment;
//                    mobile_fragment.setNavController(new GetNavController() {
//                        @Override
//                        public void onCreateNavController(NavController navController) {
//                            Loge.e("Mobile_Fragment");
//                            navController1 = navController;
//                            NavigationUI.setupActionBarWithNavController(Test6_Activity.this, navController1);
//                        }
//                    });
//                }
//            }
//        });

    }

    NavController navController1;

    @Override
    public boolean onSupportNavigateUp() {
        if (navController1 != null) return navController1.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        BackHandlerHelper.handleBackPress(this);
        //  super.onBackPressed();
        Loge.e(getSupportFragmentManager().getBackStackEntryCount() + "---");
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void registerViewModel() {
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentPreAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
                super.onFragmentPreAttached(fm, f, context);
                // Loge.e(fm.toString() + "------" + f.toString() + "---------" + context.toString());
            }
        }, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(mFragmentLifecycleCallbacks);
        Loge.e("退出");
        System.exit(0);
    }

    @Override
    protected void initEvent() {
        //   startActivity(new Intent(this, TestActivity.class));
//        binding.test6ActivityTv.setOnClickListener(new View.OnClickListener() {
//            int anInt = 0;
//
//            @Override
//            public void onClick(View v) {
        //     MyDialogFragment myDialogFragment=new MyDialogFragment();
        //      myDialogFragment.show(getSupportFragmentManager(),"dialog");
//                if (!Settings.canDrawOverlays(Test6_Activity.this)) {
//                    Loge.e("onCreate: canDrawOverlays false.");
//                    Intent intent = new Intent();
//                    intent.setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                    startActivity(intent);
//                } else {
//                    Loge.e("onCreate: canDrawOverlays true.");
//                    showWindowView();
//                }

//            }
//        });
    }

    private void showWindowView() {
        //Activity中的方法,得到窗口管理器
        WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //设置悬浮窗布局属性
        WindowManager.LayoutParams mWindowLayoutParams = new WindowManager.LayoutParams();
        //设置类型,具体有哪些值可取在后面附上
        // 设置窗体显示类型
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mWindowLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mWindowLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        //设置行为选项,具体有哪些值可取在后面附上
        mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //设置悬浮窗的显示位置
        mWindowLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
//        //设置悬浮窗的横竖屏,会影响屏幕方向,只要悬浮窗不消失,屏幕方向就会一直保持,可以强制屏幕横屏或竖屏
//        mWindowLayoutParams.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        //设置悬浮窗的宽度
        mWindowLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置悬浮窗的高度
        mWindowLayoutParams.height = 200;
        //设置悬浮窗的布局
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setBackgroundColor(Color.GRAY);
        TextView textView = new TextView(this);
        textView.setTextColor(getColor(R.color.red));
        textView.setTextSize(32);
        textView.setGravity(Gravity.CENTER);
        textView.setText("测试浮窗");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.addView(textView, layoutParams);
        //加载显示悬浮窗
        mWindowManager.addView(linearLayout, mWindowLayoutParams);
        Observable.interval(1, TimeUnit.SECONDS).take(5).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                textView.setText("测试浮窗---剩余" + (4 - aLong) + "秒");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {

            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                mWindowManager.removeView(linearLayout);
            }
        });
    }

    @Override
    protected void initData() {

    }


    @Override
    public void getMessageFromFragment(Fragment fragment, String message) {
        Loge.e(fragment.getClass().getSimpleName() + ":" + message);
    }

    public class FragmentLifecycle_02 implements DefaultLifecycleObserver {
        @Override
        public void onResume(@NonNull LifecycleOwner owner) {
            DefaultLifecycleObserver.super.onResume(owner);
            Loge.e(owner.toString() + "---onResume--");
        }
    }

    public class FragmentLifecycle implements DefaultLifecycleObserver {
        @Override
        public void onResume(@NonNull LifecycleOwner owner) {
            DefaultLifecycleObserver.super.onResume(owner);
            if (owner instanceof Dialog_Fragment) {
                Dialog_Fragment dialog_fragment = (Dialog_Fragment) owner;
                navController1 = Navigation.findNavController(dialog_fragment.getView().findViewById(R.id.nav_host_fragment_fragment_test6_dialog));
            }
            if (owner instanceof Fragment07) {
                Fragment07 fragment07 = (Fragment07) owner;
                navController1 = Navigation.findNavController(fragment07.getView().findViewById(R.id.nav_host_fragment_fragment_test6_navigation));
                fragment07.getView().findViewById(R.id.navigation_fragment_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Loge.e("AAAAAA");
                    }
                });
            }
            if (owner instanceof Mobile_Fragment) {
                Mobile_Fragment mobile_fragment = (Mobile_Fragment) owner;
                navController1 = Navigation.findNavController(mobile_fragment.getView().findViewById(R.id.nav_host_fragment_fragment_test6_mobile));
            }
            NavigationUI.setupActionBarWithNavController(Test6_Activity.this, navController1);
        }
    }

    private FragmentManager.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentPreAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
            super.onFragmentPreAttached(fm, f, context);
            Log.i(TAG, "onFragmentPreAttached: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentActivityCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
            super.onFragmentActivityCreated(fm, f, savedInstanceState);
            Log.i(TAG, "onFragmentActivityCreated: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
            super.onFragmentAttached(fm, f, context);
            Log.i(TAG, "onFragmentAttached: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentPreCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
            super.onFragmentPreCreated(fm, f, savedInstanceState);
            Log.i(TAG, "onFragmentPreCreated: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
            super.onFragmentCreated(fm, f, savedInstanceState);
            Log.i(TAG, "onFragmentCreated: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState);
            Log.i(TAG, "onFragmentViewCreated: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentStarted(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentStarted(fm, f);
            Log.i(TAG, "onFragmentStarted: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentResumed(fm, f);
            Log.i(TAG, "onFragmentResumed: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentPaused(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentPaused(fm, f);
            Log.i(TAG, "onFragmentPaused: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentStopped(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentStopped(fm, f);
            Log.i(TAG, "onFragmentStopped: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentSaveInstanceState(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Bundle outState) {
            super.onFragmentSaveInstanceState(fm, f, outState);
            Log.i(TAG, "onFragmentSaveInstanceState: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentViewDestroyed(fm, f);
            Log.i(TAG, "onFragmentViewDestroyed: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentDestroyed(fm, f);
            Log.i(TAG, "onFragmentDestroyed: " + f.getClass().getSimpleName());
        }

        @Override
        public void onFragmentDetached(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentDetached(fm, f);
            Log.i(TAG, "onFragmentDetached: " + f.getClass().getSimpleName());
        }
    };

}
