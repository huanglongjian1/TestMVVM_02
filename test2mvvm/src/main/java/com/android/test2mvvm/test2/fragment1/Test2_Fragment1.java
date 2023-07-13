package com.android.test2mvvm.test2.fragment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.base.Base_new_LazyFragment;
import com.android.test2mvvm.util.Loge;

public class Test2_Fragment1 extends Base_new_LazyFragment {

    @Override
    protected void initView() {
        String s = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            s = bundle.getString("data");
        }
        root_view.<TextView>findViewById(R.id.test2_fragment1_tv).setText(s);
    }

    public static Test2_Fragment1 newInstance(String s) {
        Test2_Fragment1 test2_fragment1 = new Test2_Fragment1();
        Bundle bundle = new Bundle();
        bundle.putString("data", s);
        test2_fragment1.setArguments(bundle);
        return test2_fragment1;
    }

    @Override
    protected View getMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test2_frament1, null);
    }

    @Override
    protected void loadData() {
        Loge.e(this.toString());
    }
}
