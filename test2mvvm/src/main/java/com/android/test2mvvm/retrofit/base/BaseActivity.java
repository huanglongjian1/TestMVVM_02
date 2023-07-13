package com.android.test2mvvm.retrofit.base;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.subjects.PublishSubject;


/**
 * Created by helin on 2016/11/11 10:41.
 */

public class BaseActivity extends AppCompatActivity {

    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();

//    @NonNull
//    public <T> Observable.Transformer<T, T> bindUntilEvent(@NonNull final ActivityLifeCycleEvent event) {
//        return new Observable.Transformer<T, T>() {
//            @Override
//            public Observable<T> call(Observable<T> sourceObservable) {
//                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
//                        lifecycleSubject.takeFirst(new Func1<ActivityLifeCycleEvent, Boolean>() {
//                            @Override
//                            public Boolean call(ActivityLifeCycleEvent activityLifeCycleEvent) {
//                                return activityLifeCycleEvent.equals(event);
//                            }
//                        });
//                return sourceObservable.takeUntil(compareLifecycleObservable);
//            }
//        };
//    }


    protected CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        compositeDisposable = new CompositeDisposable();
        super.onCreate(savedInstanceState);
        ActionBar actionbar = getSupportActionBar();
        //显示返回箭头默认是不显示的
        actionbar.setDisplayHomeAsUpEnabled(true);
        //显示左侧的返回箭头，并且返回箭头和title一直设置返回箭头才能显示
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setDisplayUseLogoEnabled(true);
        //显示标题
        actionbar.setDisplayShowTitleEnabled(true);
        String activity_name = getClass().getSimpleName();
        actionbar.setTitle(activity_name.substring(0, activity_name.indexOf("_")));


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            Loge.e("compositeDisposable取消了全部订阅");
        }
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
    }
}
