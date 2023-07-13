package com.android.test2mvvm.util.okhttp.httplib.retrofit2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.okhttp.httplib.utils.LogUtil;
import com.android.test2mvvm.util.okhttp.httplib.utils.NetWorkUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author: Page
 * @time: 17-8-4
 * @description: The thread scheduling and request preprocessing tools.
 */

public class RxSchedulers {
    private static ProgressDialog dialog;

    /**
     * @param: [context]
     * @return: io.reactivex.ObservableTransformer<T,T>
     * @description: Thread Scheduling. The default is show dialog.
     */
    public static <T> ObservableTransformer<T, T> ioMain(final Context context) {
        return ioMain(context,true);
    }

    /**
     * @param: [context, isShow] [context，Whether or not show dialog.]
     * @return: io.reactivex.ObservableTransformer<T,T>
     * @description: Thread Scheduling.
     */
    public static <T> ObservableTransformer<T, T> ioMain(final Context context, final boolean isShow) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                if (isShow && dialog == null) {
                    dialog = new ProgressDialog(context);
                    dialog.setCancelable(true);
                    dialog.setMessage("加载中...");
                }
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(final Disposable disposable) throws Exception {
                                LogUtil.d("doOnSubscribe -- Determine if there is a network and dialog.");

                                if (!NetWorkUtils.isConnectedByState(context)) {
                                    disposable.dispose();
                                    LogUtil.d("doOnSubscribe -- There's no network link.");
                                    Toast.makeText(context, "R.string.toast_network_error", Toast.LENGTH_SHORT).show();
                                } else {
                                    LogUtil.d("doOnSubscribe -- There's a network link.");
                                    if (isShow && dialog != null && !dialog.isShowing()) {
                                        dialog.show();
                                        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            @Override
                                            public void onCancel(DialogInterface dialog) {
                                                LogUtil.i("Dialog Cancel and interrupt network request!");
                                                disposable.dispose();
                                            }
                                        });
                                    }

                                }
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                if (dialog != null && dialog.isShowing())
                                    dialog.dismiss();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
