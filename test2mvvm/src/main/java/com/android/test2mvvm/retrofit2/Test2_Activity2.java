package com.android.test2mvvm.retrofit2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.R;
import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.retrofit2.base.BaseActivity;
import com.android.test2mvvm.room.AppDatabase;
import com.android.test2mvvm.room.User_stu;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.view.MyBottomSheetDialogFragment;
import com.android.test2mvvm.util.view.MyDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.disposables.ListCompositeDisposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Route(path = Constants.TEST2_ACTIVITY2)
public class Test2_Activity2 extends BaseActivity {
    AppDatabase db;

    @Override
    protected int getLayoutId() {
        //Activity中调用
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "app01.db")
                // .addMigrations(AppDatabase.MIGRATION_2_3)
                .build();

        return R.layout.test2_retrofit2_activity2;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Button button1 = findViewById(R.id.test2_activity_add_btn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.timer(0, TimeUnit.SECONDS).doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        User_stu user = new User_stu(1, new Random().nextInt(Integer.MAX_VALUE) + "", "Cat");
                        User_stu user2 = new User_stu(2, "xiao", "ming");
                        db.userDao().insertAll(user, user2);


                    }
                }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe();
            }
        });

        Button button = findViewById(R.id.test2_activity_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.create(new ObservableOnSubscribe<List<User_stu>>() {
                            @Override
                            public void subscribe(@NonNull ObservableEmitter<List<User_stu>> emitter) throws Throwable {
                                if (db.userDao().getAll().size() != 0)
                                    emitter.onNext(db.userDao().getAll());
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .flatMap(new Function<List<User_stu>, ObservableSource<User_stu>>() {
                            @Override
                            public ObservableSource<User_stu> apply(List<User_stu> user_stus) throws Throwable {
                                return Observable.fromIterable(user_stus);
                            }
                        })
                        .map(new Function<User_stu, String>() {
                            @Override
                            public String apply(User_stu user_stu) throws Throwable {
                                return user_stu.toString();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                Loge.e(s);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Loge.e(throwable.getMessage());
                            }
                        });
            }
        });
        Button update_btn = findViewById(R.id.test2_activity_update_btn);
        update_btn.setOnClickListener(new View.OnClickListener() {
            int i = 0;

            @Override
            public void onClick(View v) {
                Observable.create(new ObservableOnSubscribe<User_stu>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<User_stu> emitter) throws Throwable {
                        User_stu user_stu = new User_stu(1, "hlj" + i++, "123456");
                        db.userDao().updateUsers(user_stu);
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io()).subscribe();
            }
        });
        LiveData<User_stu> user_stuLiveData = db.userDao().findByID(1);
        TextView textView = findViewById(R.id.test2_activity_update_tv);

        user_stuLiveData.observe(this, new Observer<User_stu>() {
            int u = 0;

            @Override
            public void onChanged(User_stu user_stu) {
                if (user_stu != null) {
                    textView.setText(user_stu.toString());
                } else {
                    textView.setText("无数据" + u++);
                }

            }
        });
        Button clear_btn = findViewById(R.id.test2_activity_clear_btn);
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.timer(0, TimeUnit.SECONDS)
                        .doOnNext(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Throwable {
//                                List<User_stu> list = db.userDao().getAll();
//                                db.userDao().delete(list.toArray(new User_stu[list.size()]));
                                List<User_stu> list = db.userDao().loadAllByIds(1, 2);
                                Loge.e(list.toString());
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe();

            }
        });
        TextView livedata = findViewById(R.id.test2_activity_livedata_tv);

        Test2_Activity2_ViewModel test2_activity2_viewModel = new ViewModelProvider(this).get(Test2_Activity2_ViewModel.class);
        test2_activity2_viewModel.getListLiveData().observe(this, new Observer<List<User_stu>>() {
            @Override
            public void onChanged(List<User_stu> user_stus) {
                livedata.setText(user_stus.toString() + "\n");
                Observable.just(1, 2, 3).first(new Integer(2)).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Loge.e(integer.toString());

                        Observable.just(1).subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Throwable {

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {

                            }
                        }, new Action() {
                            @Override
                            public void run() throws Throwable {

                            }
                        });


                    }
                });
            }
        });
        livedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User_stu user_stu = new User_stu(new Random().nextInt(Integer.MAX_VALUE), "A", "B");
                        test2_activity2_viewModel.getDatabase().userDao().insertAll(user_stu);

                    }
                }).start();
            }
        });
        Button show_dialog = findViewById(R.id.test2_activity_show_dialog_btn);
        show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyDialogFragment myDialogFragment = new MyDialogFragment();
//                myDialogFragment.show(getSupportFragmentManager(), "dialog");
//
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Test2_Activity2.this);
//                View view = LayoutInflater.from(show_dialog.getContext()).inflate(R.layout.test1_activity_layout, null);
//                bottomSheetDialog.setContentView(view);
//                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//                layoutParams.height = (int) (0.75 * getResources().getDisplayMetrics().heightPixels);
//                layoutParams.width = getResources().getDisplayMetrics().widthPixels;
//                view.setLayoutParams(layoutParams);
//                bottomSheetDialog.show();
                MyBottomSheetDialogFragment myBottomSheetDialogFragment = new MyBottomSheetDialogFragment();
                myBottomSheetDialogFragment.show(getSupportFragmentManager(), "bottomdiaolog");

            }
        });
    }
}