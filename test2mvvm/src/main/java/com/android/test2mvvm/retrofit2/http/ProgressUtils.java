package com.android.test2mvvm.retrofit2.http;

import android.app.Activity;

import com.android.test2mvvm.retrofit2.DialogUtils;
import com.android.test2mvvm.util.Loge;

import java.lang.ref.WeakReference;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class ProgressUtils {
    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final Activity activity, String msg) {
        final WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);
        final DialogUtils dialogUtils = new DialogUtils();
        dialogUtils.showProgress(activityWeakReference.get());
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        }).doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                Activity context;
                                if ((context = activityWeakReference.get()) != null
                                        && !context.isFinishing()) {
                                    Loge.e("对话框结束");
                                    dialogUtils.dismissProgress();
                                }
                            }
                        })
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                        /*Activity context;
                        if ((context = activityWeakReference.get()) != null
                                && !context.isFinishing()) {
                            dialogUtils.dismissProgress();
                        }*/
                            }
                        });
            }
        };
    }

    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final Activity activity) {
        return applyProgressBar(activity, "");
    }
}
