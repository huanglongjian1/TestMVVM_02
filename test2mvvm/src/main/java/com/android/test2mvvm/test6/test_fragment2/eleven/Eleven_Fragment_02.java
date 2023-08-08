package com.android.test2mvvm.test6.test_fragment2.eleven;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ImageLayoutBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;

public class Eleven_Fragment_02 extends Util_BaseFullBottomSheetFragment<ImageLayoutBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.image_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public ElevenHandler elevenHandler = new ElevenHandler();

    public class ElevenHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                binding.test6FragmentImage.setImageBitmap((Bitmap) msg.obj);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDataLazyLoad() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
