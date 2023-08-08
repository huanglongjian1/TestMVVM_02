package com.android.test2mvvm.test6.test_fragment2.eleven;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Eleven_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    private ActivityResultLauncher activityResultLauncher;

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("跳转");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("self_define_uri");
                intent.addCategory("com.cat");
                ResolveInfo resolveInfo = getActivity().getPackageManager().resolveActivity(intent, 0);
                if (resolveInfo != null) {
                    String pkname = resolveInfo.activityInfo.packageName;
                    String activityname = resolveInfo.activityInfo.name;
                    Intent intent1 = new Intent();
                    intent1.setData(Uri.parse("content://com.android.test2mvvm.fileprovider2/inner_app_file/videoA/1690717770298.mp4"));
                    intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent1.setComponent(new ComponentName(pkname, activityname));

                    activityResultLauncher.launch(intent1);
                }
            }
        });
        binding.test6FragmentBtn2.setText("跳转");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.action", Uri.parse("test://splash/mypath?content=从html页面传过来的值"));

                activityResultLauncher.launch(intent);
            }
        });
        binding.test6FragmentBtn3.setText("跳转");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_permission.launch(Manifest.permission.CAMERA);

            }
        });
        binding.test6FragmentBtn4.setText("createDocument");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_CreateDocument.launch("hello.txt");
            }
        });
        binding.test6FragmentBtn5.setText("拍照预览");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_takePicturePreview.launch(null);
            }
        });
        binding.test6FragmentBtn6.setText("openDocument");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_OpenDocument.launch(new String[]{"image/*"});
            }
        });
        binding.test6FragmentBtn7.setText("选择多张图片");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMultiplePhoto.launch("image/*");
            }
        });

    }

    private ActivityResultLauncher launcher_permission;
    private ActivityResultLauncher launcher_CreateDocument;
    private ActivityResultLauncher launcher_takePicturePreview;
    private ActivityResultLauncher launcher_OpenDocument;
    private ActivityResultLauncher selectMultiplePhoto;

    @Override
    protected void initData() {
        launcher_permission.launch(Manifest.permission.CAMERA);
    }

    @Override
    protected void onDataLazyLoad() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        });
        launcher_permission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    Loge.e("已经获取到权限");
                } else {
                    Loge.e("缺少权限授权，对话框关闭");
                    dismiss();
                }
            }
        });
        launcher_CreateDocument = registerForActivityResult(new ActivityResultContracts.CreateDocument(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Loge.e(result.toString());
            }
        });
        launcher_takePicturePreview = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {
                Eleven_Fragment_02 eleven_fragment_02 = new Eleven_Fragment_02();
                eleven_fragment_02.show(getChildFragmentManager(), Eleven_Fragment_02.class.getSimpleName());

                Loge.e(result.toString());
                Message message = eleven_fragment_02.elevenHandler.obtainMessage();
                message.what = 1;
                message.obj = result;
                eleven_fragment_02.elevenHandler.sendMessage(message);

            }
        });
        launcher_OpenDocument = registerForActivityResult(new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Loge.e(result.toString());
            }
        });
        selectMultiplePhoto = registerForActivityResult(new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
            @Override
            public void onActivityResult(List<Uri> result) {
                Loge.e(result.toString());
            }
        });
    }

    private void openCamera(FragmentActivity activity) {
        String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                .format(new Date()) + ".png";

        File pictureFile = new File(Environment.getExternalStorageDirectory(), filename);
        if (!pictureFile.exists()) {
            try {
                pictureFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Loge.e(pictureFile.getAbsolutePath());
        Intent mIntent = new Intent();
        mIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            Uri contentUri = FileProvider.getUriForFile(activity, getActivity().getPackageName() + ".fileprovider2", pictureFile);
            //拍照结果输出到这个uri对应的file中
            mIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            //对这个uri进行授权
            mIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            //拍照结果输出到这个uri对应的file中
            mIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(pictureFile));
        }

        mIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        activity.startActivityForResult(mIntent, 10000);
    }
}
