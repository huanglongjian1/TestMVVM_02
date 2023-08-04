package com.android.test2mvvm.test6.test_fragment2.eight;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentResultListener;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.VideoViewBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

public class Eight_Fragment_02 extends Util_BaseFullBottomSheetFragment<VideoViewBinding> {
    public static final String REQUEST_KEY="video";
    public static final String BUNDLE_KEY="video_key";
    @Override
    protected int getLayoutId() {
        return R.layout.video_view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getParentFragmentManager().setFragmentResultListener(REQUEST_KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Loge.e(requestKey);
                Loge.e(result.getString(BUNDLE_KEY, "null"));
                Uri uri = Uri.parse(result.getString(BUNDLE_KEY));
                binding.test6FragmentVideoview.setVideoURI(uri);
                binding.test6FragmentVideoview.requestFocus();
                binding.test6FragmentVideoview.start();

            }
        });
        binding.test6FragmentVideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            Loge.e("播放完成");
            }
        });

    }

    @Override
    protected void onDataLazyLoad() {

    }

}
