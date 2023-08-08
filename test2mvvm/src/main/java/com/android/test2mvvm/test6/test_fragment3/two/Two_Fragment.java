package com.android.test2mvvm.test6.test_fragment3.two;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.FileProvider;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.test_fragment3.one.One_Fragment_02;
import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Two_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    private ActivityResultLauncher launcher_permissions;
    private ActivityResultLauncher getLauncher_permission;
    private ActivityResultLauncher getLauncher_permission_02;
    private ActivityResultLauncher launcher_PickContact;
    private ActivityResultLauncher launcher_takeVideo;
    private ActivityResultLauncher launcher_takePicture;
    private ActivityResultLauncher getLauncher_takeVideo;
    private Uri imageuri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcher_permissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                for (Map.Entry<String, Boolean> entry : result.entrySet()) {
                    if (entry.getValue()) {
                        Loge.e(entry.getKey() + ":" + entry.getValue());
                    } else {
                        boolean isGranted = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), entry.getKey());
                        if (isGranted) {
                            Loge.e("已经获取到授权");
                            Loge.e(entry.getKey() + ":" + entry.getValue());
                        } else {
                            Loge.e("没有取到权限,并且点击了不再询问");
                        }
                    }
                }
            }
        });
        getLauncher_permission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    Loge.e("同意");
                } else {
                    boolean isGranted = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA);
                    if (isGranted) {
                        Loge.e("解析一下");
                    } else {
//                        Loge.e("用户拒绝，并且点击了不再询问");
//                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                        getActivity().startActivity(intent);
                        //跳转应用消息，间接打开应用权限设置-效率高
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                        intent.setData(uri);
                        getActivity().startActivity(intent);

                    }
                }
            }
        });
        getLauncher_permission_02 = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    One_Fragment_02 one_fragment_02 = new One_Fragment_02();
                    one_fragment_02.show(getChildFragmentManager(), One_Fragment_02.class.getSimpleName());
                    Bundle bundle = new Bundle();
                    List<Uri> uriList = new ArrayList<>();
                    uriList.add(imageuri);
                    bundle.putParcelableArrayList("list_uri", (ArrayList<? extends Parcelable>) uriList);
                    getChildFragmentManager().setFragmentResult("uri", bundle);
                }
            }
        });
        launcher_PickContact = registerForActivityResult(new ActivityResultContracts.PickContact(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

            }
        });
        launcher_takeVideo = registerForActivityResult(new ActivityResultContracts.TakeVideo(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {

            }
        });
        launcher_takePicture = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
            }
        });

    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("申请多个权限");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_permissions.launch(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE});
            }
        });
        binding.test6FragmentBtn2.setText("申请单个权限");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLauncher_permission.launch(Manifest.permission.CAMERA);
            }
        });
        binding.test6FragmentBtn3.setText("拍照");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis() + ".png");
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
                imageuri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Loge.e(imageuri.toString());
                getLauncher_permission_02.launch(imageuri);
            }
        });
        binding.test6FragmentBtn4.setText("获取通信录");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_PickContact.launch(null);
            }
        });
        binding.test6FragmentBtn5.setText("录像");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File root = new File(getActivity().getCacheDir(), "Root");
                if (!root.exists()) {
                    root.mkdir();
                }
                File videoFile = new File(root, System.currentTimeMillis() + ".mp4");
                //  videoFile.createNewFile();
                Uri videoUri = FileProvider.getUriForFile(getContext(), getActivity().getPackageName() + ".fileprovider2", videoFile);
                launcher_takeVideo.launch(videoUri);
            }
        });
        binding.test6FragmentBtn6.setText("拍照");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File root = new File(getActivity().getCacheDir(), "picture");
                if (!root.exists()) {
                    root.mkdirs();
                }
                File fileImage = new File(root, System.currentTimeMillis() + ".png");
                Uri uriImage = FileProvider.getUriForFile(getContext(), getActivity().getPackageName() + ".fileprovider2", fileImage);
                launcher_takePicture.launch(uriImage);
            }
        });
        binding.test6FragmentBtn7.setText("test");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("test");
                File root = new File(getActivity().getCacheDir(), "video_AAA");
                if (!root.exists()) {
                    root.mkdirs();
                }
                File fileImage = new File(root, System.currentTimeMillis() + ".mp4");
                Uri uriImage = FileProvider.getUriForFile(getContext(), getActivity().getPackageName() + ".fileprovider2", fileImage);

                getLauncher_takeVideo =requireActivity().getActivityResultRegistry().register("key", new ActivityResultContracts.TakeVideo(), new ActivityResultCallback<Bitmap>() {
                    @Override
                    public void onActivityResult(Bitmap result) {

                    }
                });
                getLauncher_takeVideo.launch(uriImage);



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
    public void onDestroy() {
        super.onDestroy();

    }
}
