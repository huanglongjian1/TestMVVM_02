package com.android.test2mvvm.test6.test_fragment4.two;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.eight.Eight_Fragment_02;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.test_fragment3.one.One_Fragment_02;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Two_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("分享照片");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uriList.size() > 0) {
                    Loge.e(uriList.size() + "-");
                    systemShareWeChat(0, uriList);
                }
            }
        });
        binding.test6FragmentBtn2.setText("拍摄照片");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1000);
                } else {
                    takePicture();
                }
            }
        });
        binding.test6FragmentBtn3.setText("查询照片");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUriList.clear();
                String[] strings = {
                        MediaStore.Images.Media.DISPLAY_NAME,//名字
                        MediaStore.Images.Media.DATA,//路径
                        MediaStore.Images.Media._ID
                };
                Cursor cursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strings, null, null, null);
                Loge.e(cursor.getCount() + "-");
                Uri baseUri = Uri.parse("content://media/external/images/media");
                while (cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndexOrThrow(strings[0])) != null)
                        Loge.e(cursor.getString(cursor.getColumnIndexOrThrow(strings[0])));
                    if (cursor.getString(cursor.getColumnIndexOrThrow(strings[1])) != null)
                        Loge.e(cursor.getString(cursor.getColumnIndexOrThrow(strings[1])));
                    if (cursor.getString(cursor.getColumnIndexOrThrow(strings[2])) != null)
                        Loge.e(cursor.getString(cursor.getColumnIndexOrThrow(strings[2])));
                    Uri uri = Uri.withAppendedPath(baseUri, "" + cursor.getString(cursor.getColumnIndexOrThrow(strings[2])));
                    getUriList.add(uri);
                }
                if (getUriList.size() > 0) {
                    One_Fragment_02 one_fragment_02 = new One_Fragment_02();
                    one_fragment_02.show(getChildFragmentManager(), One_Fragment_02.class.getSimpleName());

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(One_Fragment_02.BUNDLE_KEY, (ArrayList<? extends Parcelable>) getUriList);

                    getChildFragmentManager().setFragmentResult(One_Fragment_02.REQUEST_KEY, bundle);
                }
            }
        });
        binding.test6FragmentBtn4.setText("获取通信录");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 1001);
                } else {
                    Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                    Loge.e(cursor.getCount() + "-");
                    while (cursor.moveToNext()) {
                        int name = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                        if (cursor.getString(name) != null) Loge.e(cursor.getString(name));
                        int phone = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        if (cursor.getString(phone) != null) Loge.e(cursor.getString(phone));
                    }
                    cursor.close();

                }

            }
        });
        binding.test6FragmentBtn5.setText("获取通信录_02");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings = {
                        ContactsContract.CommonDataKinds.Phone._ID,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                };
                Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, strings, null, null);
                while (cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndexOrThrow(strings[0])) != null)
                        Loge.e(cursor.getString(cursor.getColumnIndexOrThrow(strings[0])));
                    if (cursor.getString(cursor.getColumnIndexOrThrow(strings[1])) != null)
                        Loge.e(cursor.getString(cursor.getColumnIndexOrThrow(strings[1])));
                    if (cursor.getString(cursor.getColumnIndexOrThrow(strings[2])) != null)
                        Loge.e(cursor.getString(cursor.getColumnIndexOrThrow(strings[2])));
                }
            }
        });
        binding.test6FragmentBtn6.setText("查询照片");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUriList.clear();
                String[] strings = {
                        MediaStore.Images.Media.DISPLAY_NAME,//名字
                        MediaStore.Images.Media.DATA,//路径
                        MediaStore.Images.Media._ID
                };
                Cursor cursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strings, null, null);
                Loge.e(cursor.getCount() + "张照片");
                while (cursor.moveToNext()) {
                    Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cursor.getString(cursor.getColumnIndexOrThrow(strings[2])));
                    Loge.e(uri.toString());
                    getUriList.add(uri);
                }
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(One_Fragment_02.BUNDLE_KEY, (ArrayList<? extends Parcelable>) getUriList);
                getChildFragmentManager().setFragmentResult(One_Fragment_02.REQUEST_KEY, bundle);
                One_Fragment_02 one_fragment_02 = new One_Fragment_02();
                one_fragment_02.show(getChildFragmentManager(), One_Fragment_02.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn7.setText("查询视频");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUriList.clear();
                String[] strings = {
                        MediaStore.Video.Media._ID,
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.DURATION
                };
                Cursor cursor = getActivity().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, strings, null, null);
                Loge.e(cursor.getCount() + ":条视频");
                while (cursor.moveToNext()) {
                    int id = cursor.getColumnIndexOrThrow(strings[0]);
                    Uri uri = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, cursor.getString(id));
                    Loge.e(uri.toString());
                    getUriList.add(uri);
                }
                Eight_Fragment_02 eight_fragment_02 = new Eight_Fragment_02();
                eight_fragment_02.show(getChildFragmentManager(), Eight_Fragment_02.class.getSimpleName());
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Eight_Fragment_02.BUNDLE_KEY_LIST, (ArrayList<? extends Parcelable>) getUriList);
                getChildFragmentManager().setFragmentResult(Eight_Fragment_02.REQUEST_KEY, bundle);
            }
        });
    }

    List<Uri> getUriList = new ArrayList<>();

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
        requireActivity().getActivityResultRegistry().register("通信录", new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    Loge.e("获取到读取通信录权限");
                }
            }
        }).launch(Manifest.permission.READ_CONTACTS);
    }

    @Override
    protected void onDataLazyLoad() {

    }

    private List<Uri> uriList = new ArrayList<>();

    private void takePicture() {
        File root = new File(String.valueOf(Environment.getExternalStoragePublicDirectory("pic")));
        if (!root.exists()) {
            root.mkdirs();
        }
        File file_picture = new File(root, System.currentTimeMillis() + ".png");
        Uri file_uri = FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".fileprovider2", file_picture);
        requireActivity().getActivityResultRegistry().register("takePicture", new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    Loge.e(file_uri.toString());
                    uriList.add(file_uri);
                }
            }
        }).launch(file_uri);
    }

    private void systemShareWeChat(int shareTag, List<Uri> uris) {
        ComponentName comp1, comp;
        comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");//调用系统分享给微信朋友
        comp1 = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");//调用系统分享给微信朋友圈

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        if (shareTag == 0) {
            //   shareIntent.setComponent(comp1);//分享给微信朋友圈
        } else if (shareTag == 1) {
            shareIntent.setComponent(comp);//分享给微信朋友
        }
        //如果去掉shareIntent.setComponent("*");系统会调出所有的分享软件
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, (ArrayList<? extends Parcelable>) uris);
        shareIntent.setType("image/*");
        startActivity(shareIntent);
    }
}
