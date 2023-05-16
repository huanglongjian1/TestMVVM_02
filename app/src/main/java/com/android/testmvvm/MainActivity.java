package com.android.testmvvm;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.test2mvvm.test1.fragment3.Test3_Fragment;
import com.android.test2mvvm.util.Loge;
import com.android.testmvvm.rxjavaroom.CacheEntity;
import com.android.testmvvm.rxjavaroom.CacheService;
import com.android.testmvvm.rxjavaroom.DatabaseConfig;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text_app);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(getApplicationContext().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_app, new Test3_Fragment()).commit();
                textView.setText("-");
            }
        });
        Button btn_change = findViewById(R.id.btn_app);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                getDelegate().setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO ?
                        AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                // 同样需要调用recreate方法使之生效
                recreate();
            }
        });
        btn_change.setTextSize(40);

        ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Loge.e(result.getResultCode() + "-");
                    Loge.e(result.getData().getStringExtra("data_return"));
                }
            }
        });

        ActivityResultLauncher poto_Launcher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {
                //             BitmapDrawable drawable = new BitmapDrawable(result);
                ImageView imageView = findViewById(R.id.image_preview);
//                ViewGroup.LayoutParams layoutParams=imageView.getLayoutParams();
//                layoutParams.width=drawable.getIntrinsicWidth();
//                layoutParams.height=drawable.getIntrinsicHeight();
//                imageView.setLayoutParams(layoutParams);
//
                imageView.setImageBitmap(result);
                //   imageView.setBackground(drawable);

            }
        });
        ActivityResultLauncher take_Picture = registerForActivityResult(new TakeCameraUri(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                ImageView imageView = findViewById(R.id.image_preview);
                imageView.setImageURI(result);
            }
        });


        Button btn_goto = findViewById(R.id.btn_goto_app);
        btn_goto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Test1_Activity.class);
//                launcher.launch(intent);
                //         poto_Launcher.launch(null);
                //      take_Picture.launch(null);
                launchAlbum();

            }
        });
        btn_goto.setTextSize(40);

        registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    Loge.e("同意照相");
                } else {
                    Loge.e("拒绝照相");
                }
            }
        }).launch(Manifest.permission.CAMERA);
        init_new();
    }

    private void init_new() {
        CacheService.set("cache", "hlj");
        TextView textView = findViewById(R.id.text_app);
        textView.setText(CacheService.get("cache"));
        Button insert = findViewById(R.id.btn_insert_app);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheEntity cacheEntity = new CacheEntity();
                cacheEntity.setKey("insert");
                cacheEntity.setValue("today");
                CacheService.getRepository().insert(cacheEntity).subscribeOn(Schedulers.io())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Loge.e("成功插入");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Loge.e(e.getMessage());
                            }
                        });
            }
        });
        Button query = findViewById(R.id.btn_query_all_app);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Flowable<List<CacheEntity>> flowable = CacheService.getRepository().getAll();
//                flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<List<CacheEntity>>() {
//                            @Override
//                            public void accept(List<CacheEntity> cacheEntities) throws Exception {
//                                for (CacheEntity cache : cacheEntities) {
//                                    Loge.e(cache.toString());
//                                }
//                            }
//                        });

                LiveData<List<CacheEntity>> listLiveData = CacheService.getRepository().getAll_livedata();
                listLiveData.observe(MainActivity.this, new Observer<List<CacheEntity>>() {
                    @Override
                    public void onChanged(List<CacheEntity> cacheEntities) {
                        for (CacheEntity cacheEntity : cacheEntities) {
                            Loge.e(cacheEntity.toString());
                        }
                    }
                });
            }
        });
    }

    //选取图片
    final ActivityResultLauncher<String> mLauncherAlbum = registerForActivityResult(
            new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    ImageView imageView = findViewById(R.id.image_preview);
                    imageView.setImageURI(result);
                }
            }
    );

    //调用相册选择图片
    protected void launchAlbum() {
        mLauncherAlbum.launch("image/*");
    }
}