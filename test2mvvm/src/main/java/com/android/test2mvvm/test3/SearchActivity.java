package com.android.test2mvvm.test3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

@Route(path = Constants.SEARCH_ACTIVITY)
public class SearchActivity extends AppCompatActivity {

    private EditText mEtSearch;
    private TextView mTvSearch;
    private PublishSubject<String> mPublishSubject;
    private io.reactivex.rxjava3.observers.DisposableObserver<String> DisposableObserver;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mTvSearch = (TextView) findViewById(R.id.tv_search_result);
        mEtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                startSearch(s.toString());
            }
        });
        mPublishSubject = PublishSubject.create();
        DisposableObserver = new DisposableObserver<String>() {

            @Override
            public void onNext(String s) {
                mTvSearch.setText(s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };
        mPublishSubject.debounce(200, TimeUnit.MILLISECONDS).filter(new Predicate<String>() {

                    @Override
                    public boolean test(String s) throws Exception {
                        return s.length() > 0;
                    }

                }).switchMap(new Function<String, ObservableSource<String>>() {

                    @Override
                    public ObservableSource<String> apply(String query) throws Exception {
                        return getSearchObservable(query);
                    }

                }).observeOn(AndroidSchedulers.mainThread())
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Loge.e("解除订阅");
                    }
                })
                .subscribe(DisposableObserver);
        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(DisposableObserver);
    }

    private void startSearch(String query) {
        mPublishSubject.onNext(query);
    }

    private Observable<String> getSearchObservable(final String query) {
        return Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                Log.d("SearchActivity", "开始请求，关键词为：" + query);
                try {
                    Thread.sleep(100 + (long) (Math.random() * 500));
                } catch (InterruptedException e) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onError(e);
                    }
                }
                Log.d("SearchActivity", "结束请求，关键词为：" + query);
                observableEmitter.onNext("完成搜索，关键词为：" + query);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Loge.e("结束");
        mCompositeDisposable.clear();
    }
}
