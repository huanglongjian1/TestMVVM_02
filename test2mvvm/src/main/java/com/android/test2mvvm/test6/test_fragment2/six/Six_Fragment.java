package com.android.test2mvvm.test6.test_fragment2.six;

import android.Manifest;
import android.app.Notification;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentResultListener;

import com.android.test2mvvm.R;
import com.android.test2mvvm.Test2MVVMActivity;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.four.F4_Fragment_02;
import com.android.test2mvvm.test6.test_fragment2.four.F4_fragment;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.util.Map;

public class Six_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    private ActivityResultLauncher getLauncher_permission;
    private ActivityResultLauncher getLauncher_permissions;
    private ActivityResultLauncher getCameraLauncher_permission;
    private ActivityResultLauncher activityResultLauncher;
    private ActivityResultLauncher launcher_photo;
    private ActivityResultLauncher launcher_image;
    private ActivityResultLauncher launcher_video;
    private ActivityResultLauncher launcher_takePicturePreview;
    private Uri photoUri;
    private boolean isGranted = false;

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("取单个权限");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLauncher_permission.launch(Manifest.permission.READ_CONTACTS);
            }
        });
        binding.test6FragmentBtn2.setText("取多个权限");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLauncher_permissions.launch(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA});
            }
        });
        binding.test6FragmentBtn3.setText("跳转下一个activity");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Test2MVVMActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
        binding.test6FragmentBtn4.setText("拍照");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCameraLauncher_permission.launch(Manifest.permission.CAMERA);
            }
        });
        binding.test6FragmentBtn5.setText("图片选择器");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_image.launch(new String[]{"image/*"});
            }
        });
        binding.test6FragmentBtn6.setText("视频");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.test6FragmentBtn7.setText("拍照并预览");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicturePreview();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLauncher_permission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                isGranted = result;
                if (result) {
                    Loge.e("权限取到");
                } else {
                    Loge.e("用户拒绝了权限");
                }
            }
        });
        getLauncher_permissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                for (Map.Entry<String, Boolean> entry : result.entrySet()) {
                    if (entry.getValue()) {
                        Loge.e("用户授予了权限:" + entry.getKey());
                    } else {
                        Loge.e("用户拒绝了权限：" + entry.getKey());
                    }
                }
            }
        });
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        });
        launcher_photo = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    F4_Fragment_02 f4_fragment_02 = (F4_Fragment_02) F4_Fragment_02.newInstance(photoUri.toString());
                    f4_fragment_02.show(getChildFragmentManager(), F4_Fragment_02.class.getSimpleName());
                }
            }
        });
        launcher_image = registerForActivityResult(new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    Loge.e(result.toString());
                    F4_Fragment_02 f4_fragment_02 = (F4_Fragment_02) F4_Fragment_02.newInstance(result.toString());
                    f4_fragment_02.show(getChildFragmentManager(), F4_Fragment_02.class.getSimpleName());
                }
            }
        });
        getCameraLauncher_permission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "photo_" + System.currentTimeMillis());
                    contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                    photoUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    launcher_photo.launch(photoUri);
                    Loge.e(photoUri.toString() + "-----Photo地址--");
                }
            }
        });
        launcher_video = registerForActivityResult(new ActivityResultContracts.TakeVideo(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {

            }
        });
        launcher_takePicturePreview = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {
                Loge.e(result.getHeight()+":"+result.getWidth());
                Loge.e(result.toString() + ":照片");
                F4_Fragment_02 f4_fragment_02 = new F4_Fragment_02();
                Message message = Message.obtain();
                message.what = 1;
                message.obj = result;
                f4_fragment_02.getF4Handler().sendMessage(message);
                f4_fragment_02.show(getChildFragmentManager(), F4_Fragment_02.class.getSimpleName());
            }
        });

    }

    public void takePicturePreview() {
        getLauncher_permission.launch(Manifest.permission.CAMERA);
        if (isGranted) {
            launcher_takePicturePreview.launch(null);
        } else {
            Loge.e("你还没有获取到相机权限");
        }
    }
}
