package com.android.test2mvvm.test6.test_fragment3.one;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.Settings;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.core.content.PermissionChecker;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.eight.Eight_Fragment_02;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class One_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    private ActivityResultLauncher launcher_permissions;
    private ActivityResultLauncher selectMultiplePhoto;
    private ActivityResultLauncher launcher_takePicture;
    private ActivityResultLauncher launcher_CreateDocument;
    private ActivityResultLauncher launcher_takeVideo;
    private ActivityResultLauncher getLauncher_CreateDocument_Video;
    private ActivityResultLauncher launcher_deleteDocument;

    private ActivityResultLauncher launcher_takePicture2;
    Uri imageUri;
    Uri videoUri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcher_permissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                for (Map.Entry<String, Boolean> entry : result.entrySet()) {
                    Loge.e(entry.getKey() + ":" + entry.getValue());
                }
            }
        });
        selectMultiplePhoto = registerForActivityResult(new ActivityResultContracts.OpenMultipleDocuments(), new ActivityResultCallback<List<Uri>>() {
            @Override
            public void onActivityResult(List<Uri> result) {
                displayImageList(result);
            }
        });
        launcher_takePicture = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    List<Uri> uriList = new ArrayList<>();
                    uriList.add(imageUri);
                    displayImageList(uriList);
                } else {
                    try {
                        DocumentsContract.deleteDocument(getActivity().getContentResolver(), imageUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        launcher_CreateDocument = registerForActivityResult(new ActivityResultContracts.CreateDocument(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Loge.e(result.toString());
                imageUri = result;
                launcher_takePicture.launch(result);

            }
        });
        launcher_takeVideo = registerForActivityResult(new ActivityResultContracts.TakeVideo(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {

                Eight_Fragment_02 eight_fragment_02 = new Eight_Fragment_02();
                eight_fragment_02.show(getChildFragmentManager(), Eight_Fragment_02.class.getSimpleName());
                Bundle bundle = new Bundle();
                bundle.putString("video_key", videoUri.toString());
                getChildFragmentManager().setFragmentResult("video", bundle);

            }
        });
        getLauncher_CreateDocument_Video = registerForActivityResult(new ActivityResultContracts.CreateDocument(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                videoUri = result;
                launcher_takeVideo.launch(result);
            }
        });
        launcher_deleteDocument = registerForActivityResult(new ActivityResultContracts.OpenMultipleDocuments(), new ActivityResultCallback<List<Uri>>() {
            @Override
            public void onActivityResult(List<Uri> result) {
                for (Uri uri : result) {
                    try {
                        DocumentsContract.deleteDocument(getActivity().getContentResolver(), uri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        launcher_takePicture2 = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    List<Uri> uriList = new ArrayList<>();
                    uriList.add(imageUri);
                    displayImageList(uriList);
                } else {
                    try {
                        Loge.e("已经删除");
                        DocumentsContract.deleteDocument(getActivity().getContentResolver(), imageUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("多选照片预览");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMultiplePhoto.launch(new String[]{"image/*"});
            }
        });
        binding.test6FragmentBtn2.setText("拍照");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_CreateDocument.launch(System.currentTimeMillis() + ".png");
            }
        });
        binding.test6FragmentBtn3.setText("录制视频");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLauncher_CreateDocument_Video.launch(System.currentTimeMillis() + ".mp4");
            }
        });
        binding.test6FragmentBtn4.setText("删除document");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_deleteDocument.launch(new String[]{"image/*", "video/*"});
            }
        });
        binding.test6FragmentBtn5.setText("自定义URI，拍照");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Loge.e(getImageUri().toString());
                imageUri = getImageUri();
                if (imageUri == null) {
                    File root = new File(getActivity().getFilesDir(), "videoAA");
                    if (!root.exists()) {
                        root.mkdir();
                    }
                    File fileImage = new File(root, System.currentTimeMillis() + ".png");
                    if (!fileImage.exists()) {
                        try {
                            fileImage.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    imageUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", fileImage);

                }
                launcher_takePicture2.launch(imageUri);
            }
        });
        binding.test6FragmentBtn6.setText("获取URI");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = getFileUri("dir", String.valueOf(System.currentTimeMillis()).substring(0, 5) + ".png");
                Loge.e(uri.toString());
            }
        });
        binding.test6FragmentBtn7.setText("cache 的URI");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Loge.e(String.valueOf(getCacheUri("cache_test",System.currentTimeMillis()+".png")));
            }
        });
    }

    @Override
    protected void initData() {
        launcher_permissions.launch(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA
                , Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE});
    }

    @Override
    protected void onDataLazyLoad() {

    }

    public void displayImageList(List<Uri> uriList) {
        Loge.e(uriList.toString());
        One_Fragment_02 one_fragment_02 = new One_Fragment_02();
        one_fragment_02.show(getChildFragmentManager(), One_Fragment_02.class.getSimpleName());
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list_uri", (ArrayList<? extends Parcelable>) uriList);
        getChildFragmentManager().setFragmentResult("uri", bundle);
    }

    public Uri getImageUri() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(intent);
                return null;
            } else {
                File root = new File(Environment.getExternalStorageDirectory(), "video");
                if (!root.exists()) {
                    root.mkdir();
                }
                File fileImage = new File(root, System.currentTimeMillis() + ".png");
                if (!fileImage.exists()) {
                    try {
                        fileImage.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Uri imageUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", fileImage);
                return imageUri;
            }
        } else {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (PermissionChecker.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
                    launcher_permissions.launch(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE});
                } else {
                    File root = new File(Environment.getExternalStorageDirectory(), "video");
                    if (!root.exists()) {
                        root.mkdir();
                    }
                    File fileImage = new File(root, System.currentTimeMillis() + ".png");
                    if (!fileImage.exists()) {
                        try {
                            fileImage.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Uri imageUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", fileImage);
                    return imageUri;
                }
            } else {
                File root = new File(Environment.getExternalStorageDirectory(), "video");
                if (!root.exists()) {
                    root.mkdir();
                }
                File fileImage = new File(root, System.currentTimeMillis() + ".png");
                if (!fileImage.exists()) {
                    try {
                        fileImage.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Uri imageUri = Uri.fromFile(fileImage);
                return imageUri;
            }
        }
        return null;
    }

    public Uri getFileUri(String dir, String name) {
        File root = new File(getActivity().getFilesDir(), dir);
        if (!root.exists()) {
            root.mkdir();
        }
        File fileImage = new File(root, System.currentTimeMillis() + ".png");
        if (!fileImage.exists()) {
            try {
                fileImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri uri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", fileImage);
        return uri;
    }
    public Uri getCacheUri(String dir, String name) {
        File root = new File(getActivity().getCacheDir(), dir);
        if (!root.exists()) {
            root.mkdir();
        }
        File fileImage = new File(root, System.currentTimeMillis() + ".png");
        if (!fileImage.exists()) {
            try {
                fileImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri uri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", fileImage);
        return uri;
    }
}
