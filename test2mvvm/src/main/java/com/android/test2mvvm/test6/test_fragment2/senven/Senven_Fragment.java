package com.android.test2mvvm.test6.test_fragment2.senven;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

public class Senven_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("URI");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Uri uri = MyFileProvider.getUri(getContext());
                // 通过包名获取要跳转的app，创建intent对象
//                Intent intent = getActivity().getPackageManager()
//                        .getLaunchIntentForPackage("com.android.newtest");
//                // 这里如果intent为空，就说名没有安装要跳转的应用嘛
//                if (intent != null) {

//                    startActivity(intent);
//                } else {
//                    // 没有安装要跳转的app应用，提醒一下
//                    Loge.e("还没有安装这个APP");
//                }
//                Intent intent=new Intent("com.hisense.ott.home.launch");
//                intent.addCategory("android.intent.category.LAUNCHER");
//                String package_name=getActivity().getPackageName();
//                PackageManager packageManager = getActivity().getPackageManager();
//                Intent intent = packageManager.getLaunchIntentForPackage(package_name);
//
//
//                startActivity(intent);
                String package_name = "com.android.newtest";
                String activity_path = "com.android.newtest.MainActivity";
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//可选
                ComponentName comp = new ComponentName(package_name, activity_path);
                intent.setComponent(comp);
                // startActivity(intent);
                resultLauncher.launch(intent);

            }
        });
        binding.test6FragmentBtn2.setText("共享");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = MyFileProvider.getUri(getContext());
                Loge.e(uri.toString());
                String package_name = "com.android.newtest";
                Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(package_name);
                if (intent != null) {
                    intent.setData(uri);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                    startActivity(intent);
                } else {
                    Loge.e("没有找到入口activity");
                }

            }
        });
        binding.test6FragmentBtn3.setText("读取SD卡");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_permission.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                if (!isGRANTED()) {
                    return;
                }
                Files_Util.readFile("today.txt");
            }
        });
        binding.test6FragmentBtn4.setText("写入SD卡");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_permission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (!isGRANTED()) {
                    Loge.e("没有权限");
                    return;
                }
                Files_Util.createFile(getContext(), "today.txt");
            }
        });
        binding.test6FragmentBtn5.setText("skip");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.test");
                // startActivity(intent);
                activityResultLauncher.launch(intent);
            }
        });
        binding.test6FragmentBtn6.setText("query");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.android.newtest");
                if (intent != null) {
                    activityResultLauncher.launch(intent);
                } else {
                    Loge.e("intent 为null");
                }
            }
        });
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_permission.launch(Manifest.permission.CAMERA);
                Loge.e(String.valueOf(isGRANTED()));
                if (isGRANTED()) launcher_video.launch(MediaStore.ACTION_VIDEO_CAPTURE);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }

    private ActivityResultLauncher resultLauncher;
    private ActivityResultLauncher launcher_permission;
    private boolean isGRANTED = false;
    private ActivityResultLauncher activityResultLauncher;
    private ActivityResultLauncher launcher_video;

    public boolean isGRANTED() {
        return isGRANTED;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        });
        launcher_permission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                isGRANTED = result;
            }
        });
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                Loge.e(intent.getStringExtra("result") + result.getResultCode());

            }
        });
        launcher_video = registerForActivityResult(new ActivityResultContracts.TakeVideo(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {
                Loge.e(result.toString());
            }
        });
    }
}
