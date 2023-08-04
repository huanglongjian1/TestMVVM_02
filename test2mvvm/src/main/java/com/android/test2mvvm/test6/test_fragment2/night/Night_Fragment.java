package com.android.test2mvvm.test6.test_fragment2.night;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test6.test_fragment2.eight.adapter.URI_Adapter;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Random;

public class Night_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {

    private URI_Adapter uri_adapter;
    ObservableArrayList<Uri> observableArrayList = new ObservableArrayList();

    private ActivityResultLauncher launcher_permission;
    private ActivityResultLauncher launcher_take_video;
    private ActivityResultLauncher activityResultLauncher;

    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        uri_adapter = new URI_Adapter(getActivity());
        uri_adapter.setData_list(observableArrayList);
        binding.test6FragmentRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.test6FragmentRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.test6FragmentRv.setAdapter(uri_adapter);
        uri_adapter.setOnItemClickListener(new ObservableArrayList_BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Object data, int position) {
                Night_Fragment_02 night_fragment_02 = new Night_Fragment_02();
                Uri uri = (Uri) data;
                Bundle bundle = new Bundle();
                bundle.putString("video_name", uri.toString().substring(uri.toString().lastIndexOf("/") + 1));
                bundle.putString("video_uri", uri.toString());
                getChildFragmentManager().setFragmentResult("video", bundle);
                night_fragment_02.show(getChildFragmentManager(), Night_Fragment_02.class.getSimpleName());

            }
        });

        binding.test6FragmentBtn.setText("录制视频");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_permission.launch(Manifest.permission.CAMERA);
            }
        });
        binding.test6FragmentBtn2.setText("随机播放");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Night_Fragment_02 night_fragment_02 = new Night_Fragment_02();
                int i = new Random().nextInt(observableArrayList.size());
                String uri_string = String.valueOf(observableArrayList.get(i));
                Bundle bundle = new Bundle();
                bundle.putString("video_name", uri_string.substring(uri_string.lastIndexOf("/") + 1));
                bundle.putString("video_uri", uri_string);
                getChildFragmentManager().setFragmentResult("video", bundle);
                night_fragment_02.show(getChildFragmentManager(), Night_Fragment_02.class.getSimpleName());
//                night_fragment_02.setOnVideoCompletionListener(new Night_Fragment_02.OnVideoCompletionListener() {
//                    @Override
//                    public void onCompletion() {
//                        int i = new Random().nextInt(observableArrayList.size());
//                        String uri_string = String.valueOf(observableArrayList.get(i));
//                        Bundle bundle = new Bundle();
//                        bundle.putString("video_name", uri_string.substring(uri_string.lastIndexOf("/") + 1));
//                        bundle.putString("video_uri", uri_string);
//                        getChildFragmentManager().setFragmentResult("video", bundle);
//
//                    //    night_fragment_02.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
//                    }
//                });


//                night_fragment_02.getBehavior().addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//                    @Override
//                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                        Loge.e("onStateChanged");
//                        if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
//                            int i = new Random().nextInt(observableArrayList.size());
//                            String uri_string = String.valueOf(observableArrayList.get(i));
//                            Bundle bundle = new Bundle();
//                            bundle.putString("video_name", uri_string.substring(uri_string.lastIndexOf("/") + 1));
//                            bundle.putString("video_uri", uri_string);
//                            getChildFragmentManager().setFragmentResult("video", bundle);
//                            night_fragment_02.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
//                        }
//                    }
//
//                    @Override
//                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//                    }
//                });
            }
        });
        binding.test6FragmentBtn3.setText("跳转");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = new Random().nextInt(observableArrayList.size());

                Uri uri = observableArrayList.get(i);
                Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.android.newtest");
                if (intent != null) {
                    intent.setData(uri);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    activityResultLauncher.launch(intent);

                } else {
                    Loge.e("intent 为null");
                }
            }
        });


    }

    @Override
    protected void initData() {
        File root = new File(getActivity().getFilesDir(), "videoA");
        if (root.exists()) {
            File[] files = root.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    Loge.e(pathname.getName());
                    return pathname.getName().contains("mp4");
                }
            });
            Uri fileUri;
            for (File file : files) {
                fileUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", file);
                observableArrayList.add(fileUri);
                Loge.e(fileUri.toString());
            }
        }

        getChildFragmentManager().setFragmentResultListener("onCompletion", Night_Fragment.this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (requestKey != "onCompletion") return;
                int i = new Random().nextInt(observableArrayList.size());
                String uri_string = String.valueOf(observableArrayList.get(i));
                Bundle bundle = new Bundle();
                bundle.putString("video_name", uri_string.substring(uri_string.lastIndexOf("/") + 1));
                bundle.putString("video_uri", uri_string);
                getChildFragmentManager().setFragmentResult("video", bundle);
            }
        });
    }

    @Override
    protected void onDataLazyLoad() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcher_permission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                File root = new File(getActivity().getFilesDir(), "videoA");
                if (!root.exists()) {
                    root.mkdir();
                }
                File filemp4 = new File(root, System.currentTimeMillis() + ".mp4");
                Uri urimp4 = null;
                try {
                    filemp4.createNewFile();
                    urimp4 = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", filemp4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (result) launcher_take_video.launch(urimp4);
                observableArrayList.add(urimp4);
            }
        });
        launcher_take_video = registerForActivityResult(new ActivityResultContracts.TakeVideo(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {

            }
        });
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Loge.e(result.getResultCode() + result.getData().toString());
            }
        });

    }
}
