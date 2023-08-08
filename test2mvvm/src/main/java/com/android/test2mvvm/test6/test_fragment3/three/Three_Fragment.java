package com.android.test2mvvm.test6.test_fragment3.three;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.fragments.newinstance.test07.TestActivity;
import com.android.test2mvvm.test6.test_fragment2.Test2_Fragment_Activity;
import com.android.test2mvvm.test6.test_fragment2.eight.Eight_Fragment_02;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.test_fragment3.one.One_Fragment_02;
import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Three_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }


    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("拍照");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = getUri("test_Picture", ".png");
                requireActivity().getActivityResultRegistry().register("takePicture", new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        One_Fragment_02 one_fragment_02 = new One_Fragment_02();
                        one_fragment_02.show(getChildFragmentManager(), One_Fragment_02.class.getSimpleName());
                        List<Uri> uriList = new ArrayList<>();
                        if (result) {
                            uriList.add(uri);
                        }
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(One_Fragment_02.BUNDLE_KEY, (ArrayList<? extends Parcelable>) uriList);
                        getChildFragmentManager().setFragmentResult(One_Fragment_02.REQUEST_KEY, bundle);
                    }
                }).launch(uri);
            }
        });
        binding.test6FragmentBtn2.setText("录制视频");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = getUri("test_Video", ".mp4");
                requireActivity().getActivityResultRegistry().register("takeVideo", new ActivityResultContracts.TakeVideo(), new ActivityResultCallback<Bitmap>() {
                    @Override
                    public void onActivityResult(Bitmap result) {
                        Eight_Fragment_02 eight_fragment_02 = new Eight_Fragment_02();
                        eight_fragment_02.show(getChildFragmentManager(), Eight_Fragment_02.class.getSimpleName());
                        Bundle bundle = new Bundle();
                        bundle.putString(Eight_Fragment_02.BUNDLE_KEY, uri.toString());
                        getChildFragmentManager().setFragmentResult(Eight_Fragment_02.REQUEST_KEY, bundle);


                    }
                }).launch(uri);
            }
        });
        binding.test6FragmentBtn3.setText("申请单个权限");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("permission", new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if (result) {
                            Loge.e("获取到权限");
                        } else if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            Loge.e("展示给用户，申请这个权限的必要性，只有一次展示机会");
                        } else {
                            Loge.e("用户没有给权限，拜拜");
                            //  requireActivity().finish();
                        }
                    }
                }).launch(Manifest.permission.CAMERA);
            }
        });
        binding.test6FragmentBtn4.setText("申请多个权限");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("permissions", new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
                    @Override
                    public void onActivityResult(Map<String, Boolean> result) {
                        for (Map.Entry<String, Boolean> entry : result.entrySet()) {
                            if (entry.getValue()) {
                                Loge.e(entry.getKey() + ":" + entry.getValue());
                            } else {
                                Loge.e("用户没有同意这个权限:" + entry.getKey());
                            }
                        }
                    }
                }).launch(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS});
            }
        });
        binding.test6FragmentBtn5.setText("文件选择器");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("GetContent", new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Loge.e(result.toString());
                    }
                }).launch("image/*");
            }
        });
        binding.test6FragmentBtn6.setText("一次选多个文件");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("", new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
                    @Override
                    public void onActivityResult(List<Uri> result) {
                        Loge.e(result.toString());
                    }
                }).launch("image/*");
            }
        });
        binding.test6FragmentBtn7.setText("跳转下一个activity");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("StartActivityForResult", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                    }
                }).launch(new Intent(getActivity(), Test2_Fragment_Activity.class));
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }

    private Uri getUri(String dir, String suffix) {
        File root = new File(requireActivity().getCacheDir(), dir);
        if (!root.exists()) {
            root.mkdirs();
        }
        File file = new File(root, System.currentTimeMillis() + suffix);
        Uri uri = FileProvider.getUriForFile(requireActivity(), requireActivity().getPackageName() + ".fileprovider2", file);
        return uri;
    }
}
