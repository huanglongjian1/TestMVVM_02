package com.android.test2mvvm.test6.test_fragment2.eight;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentResultListener;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.VideoViewBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;

public class Eight_Fragment_02 extends Util_BaseFullBottomSheetFragment<VideoViewBinding> {
    public static final String REQUEST_KEY = "video";
    public static final String BUNDLE_KEY = "video_key";
    public static final String BUNDLE_KEY_LIST = "video_key_list";

    @Override
    protected int getLayoutId() {
        return R.layout.video_view;
    }

    @Override
    protected void initView() {

    }

    List<Uri> uriList;
    int index;

    @Override
    protected void initData() {
        getParentFragmentManager().setFragmentResultListener(REQUEST_KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Loge.e(requestKey);
                if (result.getString(BUNDLE_KEY) != null) Loge.e(result.getString(BUNDLE_KEY));
                if (!TextUtils.isEmpty(result.getString(BUNDLE_KEY))) {
                    Uri uri = Uri.parse(result.getString(BUNDLE_KEY));
                    playVideo(uri);
                }
                uriList = result.getParcelableArrayList(BUNDLE_KEY_LIST);
                if (uriList != null && uriList.size() > 0) {
                    Loge.e(uriList.size() + "-----");
                    playVideo(uriList.get(0));
                }

            }
        });
        binding.test6FragmentVideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (index < uriList.size()-1) {
                    Loge.e(index + ":播放完成");
                } else {
                    Loge.e(index + "播放结束");
                }
                index++;
                if (uriList != null && uriList.size() > 0 && index < uriList.size()) {
                    playVideo(uriList.get(index));
                }
            }
        });

    }

    @Override
    protected void onDataLazyLoad() {

    }

    private void playVideo(Uri uri) {
        binding.test6FragmentVideoview.setVideoURI(uri);
        binding.test6FragmentVideoview.requestFocus();
        binding.test6FragmentVideoview.start();
    }

}
