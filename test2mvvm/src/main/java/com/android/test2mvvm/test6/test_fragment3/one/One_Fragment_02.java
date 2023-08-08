package com.android.test2mvvm.test6.test_fragment3.one;

import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentResultListener;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ItemLayouBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.util.List;

public class One_Fragment_02 extends Util_BaseFullBottomSheetFragment<ItemLayouBinding> {
    public static final String REQUEST_KEY = "uri";
    public static final String BUNDLE_KEY = "list_uri";

    @Override
    protected int getLayoutId() {
        return R.layout.item_layou;
    }

    @Override
    protected void initView() {
        getParentFragmentManager().setFragmentResultListener(REQUEST_KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                LinearLayout linearLayout = (LinearLayout) binding.itemTv.getParent();
                List<Uri> uriList = result.getParcelableArrayList(BUNDLE_KEY);
                for (Uri uri : uriList) {
                    ImageView imageView = new ImageView(getContext());
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearLayout.addView(imageView, layoutParams);
                    imageView.setImageURI(uri);
                }
                ScrollView scrollView = (ScrollView) linearLayout.getParent();
                scrollView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (scrollView.getScrollY() > 0)
                            scrollView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {
        binding.itemTv.setText(this.getClass().getSimpleName());
    }

    @Override
    protected void onDataLazyLoad() {

    }
}
