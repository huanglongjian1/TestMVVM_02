package com.android.test2mvvm.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.android.test2mvvm.login.Login_Activity;
import com.android.test2mvvm.test5.fragment6.MyPresent;
import com.android.test2mvvm.util.ActivityManager;
import com.android.test2mvvm.util.Loge;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

//ViewDataBinding 是所有DataBinding的父类
public abstract class BaseActivity<VM extends BaseViewModel, VDB extends ViewDataBinding> extends AppCompatActivity {
    //获取当前activity布局文件,并初始化我们的binding
    protected abstract int getContentViewId();

    //处理逻辑业务
    protected abstract void processLogic();

    protected abstract void registerViewModel();

    protected abstract void initEvent();

    protected abstract void initData();

    protected VM mViewModel;
    protected VDB binding;
    protected ProgressDialog mProgressDialog;

    protected MyPresent myPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new MyPresent()); //添加监听对象

        setTitle(this.getClass().getSimpleName());
        // setContentView(getContentViewId());
        compositeDisposable = new CompositeDisposable();
        ActivityManager.getInstance().addActivity(this);
        //初始化我们的binging
        binding = DataBindingUtil.setContentView(this, getContentViewId());
        //给binding加上感知生命周期，AppCompatActivity就是lifeOwner，之前解释过了，不懂看前面
        setContentView(binding.getRoot());
        binding.setLifecycleOwner(this);
        //创建我们的ViewModel。
        initProgressDialog();
        createViewModel();
        processLogic();
        initEvent();
        initData();
        registerViewModel();

    }

    protected void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在奋力的加载中...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
    }

    public void createViewModel() {
        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) new ViewModelProvider(this).get(modelClass);
        }
    }

    MyReceiver receiver;

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter fliter = new IntentFilter();
        fliter.addAction("OffLine");
        receiver = new MyReceiver();
        registerReceiver(receiver, fliter);
    }

    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builer = new AlertDialog.Builder(context);
            builer.setTitle("强制下线广播").setMessage("你已被强制下线，请重新登陆。").setCancelable(true).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //  finish();
                    ActivityManager.getInstance().finishAllActivity();
                    Intent in = new Intent(context, Login_Activity.class);
                    in.putExtra("context", context.getClass().getName());
                    startActivity(in);
                    Loge.e(context.getClass().getName());

                    //   ARouter.getInstance().build(Constants.LOGIN_ACTIVITY).navigation();
                }
            }).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
        compositeDisposable.clear();
    }

    protected CompositeDisposable compositeDisposable;


}
