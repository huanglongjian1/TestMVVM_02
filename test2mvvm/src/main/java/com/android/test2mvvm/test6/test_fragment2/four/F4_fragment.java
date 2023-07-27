package com.android.test2mvvm.test6.test_fragment2.four;

import android.Manifest;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

public class F4_fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Android10开始必须由系统自动分配路径，同时该方式也能自动刷新相册
                ContentValues values = new ContentValues();
                // 指定图片文件的名称
                values.put(MediaStore.Images.Media.DISPLAY_NAME, "photo_" + System.currentTimeMillis());
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");// 类型为图像
                // 通过内容解析器插入一条外部内容的路径信息
                mImageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                launcherOriginal.launch(mImageUri);
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcherPermission.launch(Manifest.permission.CAMERA);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }

    private ActivityResultLauncher launcherPermission;
    private ActivityResultLauncher launcherOriginal;
    private Uri mImageUri;// 图片的路径对象

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcherOriginal = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                Loge.e(String.valueOf(result) + "==========");
                if (result) {
                    F4_Fragment_02 f4_fragment_02 = (F4_Fragment_02) F4_Fragment_02.newInstance(mImageUri.toString());
                    f4_fragment_02.show(getChildFragmentManager(), F4_Fragment_02.class.getSimpleName());
                }
            }
        });
        launcherPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    Loge.e("拿到相机权限");
                } else {
                    Loge.e("相机权限拒绝");
                }
            }
        });
    }
}
