package com.android.test2mvvm.test3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.R;
import com.android.test2mvvm.retrofit.base.ActivityLifeCycleEvent;
import com.android.test2mvvm.retrofit.base.BaseActivity;
import com.android.test2mvvm.retrofit.enity.User;
import com.android.test2mvvm.retrofit.http.Api;
import com.android.test2mvvm.retrofit.http.ApiService;
import com.android.test2mvvm.retrofit.http.HttpUtil;
import com.android.test2mvvm.retrofit.http.ProgressSubscriber;
import com.android.test2mvvm.retrofit.http.Url;
import com.android.test2mvvm.retrofit2.DialogUtils;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Route(path = Constants.TEST_RETROFIT_ACTIVITY)
public class Test_retrofit_Activity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_retrofit_activity);
        TextView textView = findViewById(R.id.test_retrofit_tv);

        DialogUtils dialogUtils=new DialogUtils();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Observable observable = Api.getDefault(Url.BASE_URL).getTopMovie(1, 1);
//
//                HttpUtil.getInstance().toSubscribe(observable, new ProgressSubscriber<User>(Test_retrofit_Activity.this) {
//
//
//                    @Override
//                    protected void _onNext(User user) {
//                        Loge.e(user.toString());
//                    }
//
//                    @Override
//                    protected void _onError(String message) {
//
//                    }
//                }, "httpResult", ActivityLifeCycleEvent.DESTROY, lifecycleSubject, false, false);
////
//                HttpResult<String> httpResult = new HttpResult(1, "aaa", 1, 1, "测试httpresult");
//                Hawk.put("test", httpResult);
           dialogUtils.dismissProgress();
            }

        });
        TextView textView1 = findViewById(R.id.test_hawk_tv);
        textView1.setOnClickListener(new View.OnClickListener() {
            int i = 0;

            @Override
            public void onClick(View v) {

                dialogUtils.showProgress(Test_retrofit_Activity.this);
            }
        });

    }


}
