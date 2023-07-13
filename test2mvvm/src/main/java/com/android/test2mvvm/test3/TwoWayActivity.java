package com.android.test2mvvm.test3;


import android.graphics.Color;
import android.os.Bundle;
import android.transition.ChangeTransform;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.transition.AutoTransition;
import androidx.transition.Scene;
import androidx.transition.TransitionManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ActivityTwoWayBinding;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


/**
 * @author markzhai on 16/7/7
 * @version 1.0.0
 */
@Route(path = Constants.TWOWAY_ACTIVITY)
public class TwoWayActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityTwoWayBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // overridePendingTransition(R.anim.translate_left_in,0);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Slide().setDuration(5000));
        getWindow().setExitTransition(new Slide().setDuration(5000));
        Transition transition1 = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_bottom);
        getWindow().setReenterTransition(transition1);
        android.transition.Transition transition2 = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        getWindow().setReturnTransition(transition2);

        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_two_way);
        setTitle("今晚打老虎");
        FormModel model = new FormModel("markzhai", "123456");
        model.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (BR.name == i) {
                    Loge.e("名字改变");
                }
                if (BR.password == i) {
                    Loge.e("密码变动");
                }
            }
        });
        mBinding.setModel(model);

        Scene scene1 = Scene.getSceneForLayout(mBinding.test3TwowayFragment, R.layout.scene1, TwoWayActivity.this);
        Scene scene2 = Scene.getSceneForLayout(mBinding.test3TwowayFragment, R.layout.scene2, TwoWayActivity.this);
        Scene scene3 = new Scene(mBinding.test3TwowayFragment, mBinding.test3TwowayImage);
        mBinding.test3WelcomeBtn.setOnClickListener(new View.OnClickListener() {
            boolean togger = true;

            @Override
            public void onClick(View v) {

//                if (togger) {
//                    TransitionManager.go(scene1, new Explode().setDuration(5000));
//                } else {
//                    TransitionManager.go(scene2, new Explode().setDuration(5000));
//                }
//                togger = !togger;
                TransitionManager.beginDelayedTransition((ViewGroup) mBinding.test3TwowayFragment.getParent(), new AutoTransition());
                mBinding.test3TwowayImage.setPadding(100, 100, 100, 100);

                ChangeTransform changeTransform = new ChangeTransform();
                changeTransform.setDuration(5000);
                android.transition.TransitionManager.beginDelayedTransition((ViewGroup) mBinding.test3TwowayImage.getParent(), changeTransform);
                mBinding.test3TwowayImage.setRotation(45);

            }


        });
        mBinding.test3SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Loge.e("被刷新了");
                mBinding.test3SwipeRefreshLayout.setRefreshing(false);
            }
        });
        setSupportActionBar(mBinding.test3TwowayToolbar);
        mBinding.test3TwowayToolbar.setNavigationIcon(R.drawable.ic_loading);
        mBinding.test3TwowayToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("明晚也要打老虎");
        actionBar.setSubtitle("明晚肖老师");
        mBinding.test3TwowayToolbar.setLogo(R.drawable.ic_loading);
        mBinding.test3TwowayToolbar.setLogoDescription("一天一个杨");
        mBinding.test3TwowayToolbar.setTitleTextColor(Color.RED);
        mBinding.test3TwowayToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(actionBar.getTitle().toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    //重写onOptionsItemSelected(MenuItem item) ，处理各个按钮的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "you clicked Backup", Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(R.id.backup), "今晚打老虎", Snackbar.LENGTH_INDEFINITE)
                        .setAction("吃饱了把", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Loge.e("邮件中");
                            }
                        }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                super.onDismissed(transientBottomBar, event);
                                Loge.e("onDismissed");
                            }

                            @Override
                            public void onShown(Snackbar transientBottomBar) {
                                super.onShown(transientBottomBar);
                                Loge.e("onShown");
                            }
                        })
                        .show();
                break;
            case R.id.delete:
                Toast.makeText(this, "you clicked Delete", Toast.LENGTH_SHORT).show();
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TwoWayActivity.this);
                View view = LayoutInflater.from(bottomSheetDialog.getContext()).inflate(R.layout.test1_fragment8, null);
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();


                break;
            case R.id.setting:
                Toast.makeText(this, "you clicked Setting", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Loge.e("AAA");
        ((ViewGroup) mBinding.test3WelcomeBtn.getParent()).setVisibility(View.VISIBLE);

        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }

}
