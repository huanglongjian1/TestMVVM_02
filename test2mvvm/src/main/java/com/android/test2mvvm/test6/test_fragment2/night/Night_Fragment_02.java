package com.android.test2mvvm.test6.test_fragment2.night;

import android.media.MediaPlayer;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentResultListener;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.VideoViewBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class Night_Fragment_02 extends Util_BaseFullBottomSheetFragment<VideoViewBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.video_view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getParentFragmentManager().setFragmentResultListener("video", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (result != null) {
                    Uri uri_video = Uri.parse(result.getString("video_uri"));
                    if (uri_video != null) {
                        binding.test6FragmentVideoview.setVideoURI(uri_video);
                        MediaController mediaController = new MediaController(getActivity());
                        Loge.e(mediaController.toString());
                        binding.test6FragmentVideoview.setMediaController(mediaController);//VideoView和MediaController关联
                        VideoView videoView = binding.test6FragmentVideoview;
                        binding.test6FragmentVideoview.requestFocus();
                        binding.test6FragmentVideoview.start();
                    }
                    String name = result.getString("video_name");
                    if (!TextUtils.isEmpty(name)) {
                        binding.test6Fragment8Tv.setText(name);
                    }
                }

            }
        });

        binding.test6FragmentVideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // getBehavior().setPeekHeight(100);
                //  getBehavior().setState(BottomSheetBehavior.STATE_COLLAPSED);
                //   if (videoCompletionListener!=null)videoCompletionListener.onCompletion();
                getParentFragmentManager().setFragmentResult("onCompletion", new Bundle());
            }
        });
    }

    @Override
    protected void onDataLazyLoad() {

    }

    OnVideoCompletionListener videoCompletionListener;

    public void setOnVideoCompletionListener(OnVideoCompletionListener onVideoCompletionListener) {
        videoCompletionListener = onVideoCompletionListener;
    }

    public interface OnVideoCompletionListener {
        public void onCompletion();
    }
}
