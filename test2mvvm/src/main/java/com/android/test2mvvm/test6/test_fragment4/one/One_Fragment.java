package com.android.test2mvvm.test6.test_fragment4.one;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.okhttp.httplib.utils.LogUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class One_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("访问android/data");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata");
                Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                intent1.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                        | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
                intent1.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri1);
                requireActivity().getActivityResultRegistry().register("tree", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Loge.e(result.getData().getData().toString());
                        DocumentFile root = DocumentFile.fromTreeUri(getActivity(), result.getData().getData());
                        for (DocumentFile documentFile : root.listFiles()) {
                            Loge.e(documentFile.getName());
                            try {
                                InputStream inputStream = getActivity().getContentResolver().openInputStream(documentFile.getUri());
                                byte[] bytes = new byte[1024];
                                StringBuilder stringBuilder = new StringBuilder();
                                int length;
                                while ((length = inputStream.read(bytes)) != -1) {
                                    stringBuilder.append(new String(bytes, 0, length));
                                }
                                Loge.e(stringBuilder.toString());
                                inputStream.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).launch(intent1);
            }
        });
        binding.test6FragmentBtn2.setText("media");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查找获取cursor，怎么获取该数据
                Cursor cursor = getActivity().getContentResolver().query(
                        MediaStore.Files.getContentUri("external"),
                        null,
                        null,
                        null,
                        null);
//图片路径所在列的索引
                int indexPhotoPath = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                while (cursor.moveToNext()) {
                    //打印图片的路径
                    Log.e("uri:", cursor.getString(indexPhotoPath));
                }
                Loge.e(String.valueOf(cursor.getCount()));
                cursor.close();


            }
        });
        binding.test6FragmentBtn3.setText("media_02");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUriColumns(albumUri);
            }
        });
        binding.test6FragmentBtn4.setText("media_03");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取指定字段
                String[] columns = new String[]{MediaStore.Files.FileColumns._ID, MediaStore.Files.FileColumns.MIME_TYPE, MediaStore.Files.FileColumns
                        .SIZE, MediaStore.Files.FileColumns.DATE_MODIFIED, MediaStore.Files.FileColumns.DATA};
                Cursor c = getActivity().getContentResolver().query(MediaStore.Files.getContentUri("external"), columns, null, null, null);
                Loge.e(String.valueOf(c.getCount()));
                c.moveToFirst();
                while (c.moveToNext()) {
                    Loge.e(c.getString(c.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)));
                    if (c.getString(c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)) != null)
                        Loge.e(c.getString(c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)));
                    if (c.getString(c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)) != null)
                        Loge.e(c.getString(c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)));
                    if (c.getString(c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)) != null)
                        Loge.e(c.getString(c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)));
                    if (c.getString(c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)) != null)
                        Loge.e(c.getString(c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)));
                }
            }
        });
        binding.test6FragmentBtn5.setText("拍摄视频");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1000);
                } else {
                    File root_video = new File(String.valueOf(Environment.getExternalStoragePublicDirectory("Video")));
                    if (!root_video.exists()) {
                        root_video.mkdirs();
                    }
                    File file_video = new File(root_video, System.currentTimeMillis() + ".mp4");
                    Uri uri_video = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", file_video);
                    requireActivity().getActivityResultRegistry().register("takevideo", new ActivityResultContracts.TakeVideo(), new ActivityResultCallback<Bitmap>() {
                        @Override
                        public void onActivityResult(Bitmap result) {

                        }
                    }).launch(uri_video);
                }
            }
        });
        binding.test6FragmentBtn6.setText("查询视频");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取内容提供者
                ContentResolver contentResolver = getActivity().getContentResolver();
//需要获取的视频信息
                String[] strings = {
                        MediaStore.Video.Media.DISPLAY_NAME,  //视频在文件sdcard的名称
                        MediaStore.Video.Media.SIZE,  //视频大小
                        MediaStore.Video.Media.DURATION,  //视频总时长
                        MediaStore.Video.Media.DATA  //视频路径
                };
//查询数据
                Cursor cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, strings, null, null, null);
                Loge.e(String.valueOf(cursor.getCount()));

                while (cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)) != null)
                        Loge.e(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));
                    if (cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)) != null)
                        Loge.e(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));
                    if (cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)) != null)
                        Loge.e(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)));
                    if (cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)) != null)
                        Loge.e(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                }
            }
        });
        binding.test6FragmentBtn7.setText("访问视频");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("video", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Loge.e(result.toString());
                    }
                }).launch(new String[]{"video/*"});
            }
        });
    }

    @Override
    protected void initData() {
        requireActivity().getActivityResultRegistry().register("权限", new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (!result) {
                    getBehavior().setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        }).launch(Manifest.permission.CAMERA);
    }

    @Override
    protected void onDataLazyLoad() {

    }

    private Uri albumUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

    private void getUriColumns(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String[] columns = cursor.getColumnNames();
        for (String string : columns) {
            Loge.e(cursor.getColumnIndex(string) + " = " + string);
        }
    }

}
