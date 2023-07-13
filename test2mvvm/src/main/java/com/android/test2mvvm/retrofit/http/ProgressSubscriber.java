package com.android.test2mvvm.retrofit.http;


import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.android.test2mvvm.retrofit.enity.HttpResult;
import com.android.test2mvvm.retrofit.enity.User;
import com.android.test2mvvm.retrofit.view.SimpleLoadDialog;
import com.android.test2mvvm.util.Loge;

import org.reactivestreams.Subscriber;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * Created by helin on 2016/10/10 15:49.
 */

public abstract class ProgressSubscriber<T> implements ProgressCancelListener, Observer<HttpResult<T>> {


    private SimpleLoadDialog dialogHandler;

    public ProgressSubscriber(Context context) {
        dialogHandler = new SimpleLoadDialog(context, this, true);
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    /**
     * 显示Dialog
     */
    public void showProgressDialog() {
        if (dialogHandler != null) {
//            dialogHandler.obtainMessage(SimpleLoadDialog.SHOW_PROGRESS_DIALOG).sendToTarget();
            dialogHandler.show();
        }
    }

    @Override
    public void onNext(@NonNull HttpResult<T> tHttpResult) {
        T t = tHttpResult.getSubjects();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _onNext(t);
        dismissProgressDialog();
    }

    /**
     * 隐藏Dialog
     */
    public void dismissProgressDialog() {
        if (dialogHandler != null) {
//            dialogHandler.obtainMessage(SimpleLoadDialog.DISMISS_PROGRESS_DIALOG).sendToTarget();
            dialogHandler.dismiss();
            ;
            dialogHandler = null;
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (false) { //这里自行替换判断网络的代码
            _onError("网络不可用");
        } else if (e instanceof ApiException) {
            _onError(e.getMessage());
        } else {
            _onError("请求失败，请稍后再试...");
        }
        dismissProgressDialog();
    }

    Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
    }

    @Override
    public void onCancelProgress() {
        if (!disposable.isDisposed()) {
            this.disposable.dispose();
            Loge.e("任务取消了");
        }

    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}
