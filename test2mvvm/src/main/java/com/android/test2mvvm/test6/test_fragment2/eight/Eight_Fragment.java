package com.android.test2mvvm.test6.test_fragment2.eight;

import android.Manifest;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test6.test_fragment2.eight.adapter.URI_Adapter;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Eight_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("录像");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_permission.launch(Manifest.permission.CAMERA);
            }
        });
        binding.test6FragmentBtn2.setText("播放视频");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("content://com.android.test2mvvm.fileprovider2/inner_app_file/videoA/1690603940422.mp4");
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Log.e("URI:::::::::", uri.toString());
//                intent.setDataAndType(uri, "video/mp4");
//                startActivity(intent);


                eight_fragment_02.show(getChildFragmentManager(), Eight_Fragment_02.class.getSimpleName());
                Bundle bundle = new Bundle();
                if (video_uri != null) {
                    bundle.putString("video_key", video_uri.toString());
                    getChildFragmentManager().setFragmentResult("video", bundle);
                }


            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    Eight_Fragment_02 eight_fragment_02 = new Eight_Fragment_02();
    @Override
    protected void initData() {
        List<Uri> uriList = new ArrayList<>();
        File root = new File(getActivity().getFilesDir(), "videoA");
        if (root.exists()) {
            File[] files = root.listFiles();


            Uri fileuri = null;
            for (int i = 0; i < files.length; i++) {
                Log.e("TAG", files[i].getName());
                // Use the FileProvider to get a content URI
                try {
                    fileuri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", files[i]);
                    // add current file uri to the list
                    uriList.add(fileuri);
                } catch (Exception e) {
                    Log.e("TAG", "The selected file can't be shared: " + files[i].getPath());
                    fileuri = null;
                }
            }
        }
        ObservableArrayList<Uri> observableArrayList = new ObservableArrayList();
        for (Uri uri_adapter : uriList) {
            observableArrayList.add(uri_adapter);
        }
        URI_Adapter uri_adapter = new URI_Adapter(getActivity());
        uri_adapter.setData_list(observableArrayList);
        binding.test6FragmentRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.test6FragmentRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.test6FragmentRv.setAdapter(uri_adapter);
        uri_adapter.setOnItemClickListener(new ObservableArrayList_BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Object data, int position) {
                Uri uri = (Uri) data;
                eight_fragment_02.show(getChildFragmentManager(), Eight_Fragment_02.class.getSimpleName());
                Bundle bundle = new Bundle();
                if (uri != null) bundle.putString("video_key", uri.toString());
                getChildFragmentManager().setFragmentResult("video", bundle);


            }
        });


    }

    @Override
    protected void onDataLazyLoad() {

    }

    private ActivityResultLauncher launcher_takeVideo;
    private ActivityResultLauncher launcher_permission;
    private Uri video_uri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcher_permission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    File root = new File(getActivity().getFilesDir(), "videoA");
                    if (!root.exists()) root.mkdir();
                    File mp4path = new File(root, System.currentTimeMillis() + ".mp4");
                    Uri mp4uri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", mp4path);
                    Loge.e(mp4uri.toString());
                    launcher_takeVideo.launch(mp4uri);
                    URI_Adapter uri_adapter = (URI_Adapter) binding.test6FragmentRv.getAdapter();
                    uri_adapter.add(mp4uri);

                    video_uri = mp4uri;
                }

            }
        });
        launcher_takeVideo = registerForActivityResult(new ActivityResultContracts.TakeVideo(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {

            }
        });
    }
}
