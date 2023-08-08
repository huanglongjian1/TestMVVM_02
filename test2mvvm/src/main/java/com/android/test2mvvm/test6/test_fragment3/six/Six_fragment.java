package com.android.test2mvvm.test6.test_fragment3.six;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentABinding;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.test_fragment3.six.adapter.DownLoad_Adapter;
import com.android.test2mvvm.test6.test_fragment3.six.downbean.DownLoad_Bean;

import java.util.ArrayList;
import java.util.List;

public class Six_fragment extends Util_BaseFullBottomSheetFragment<ResultFragmentABinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.result_fragment_a;
    }

    ObservableArrayList<DownLoad_Bean> downLoad_beanObservableArrayList = new ObservableArrayList<>();


    @Override
    protected void initView() {
        DownLoad_Adapter downLoad_adapter = new DownLoad_Adapter(getContext());
        downLoad_adapter.setData_list(downLoad_beanObservableArrayList);
        binding.resultFragmentARecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.resultFragmentARecyclerview.setAdapter(downLoad_adapter);

    }

    @Override
    protected void initData() {

            DownLoad_Util downLoad_util = new DownLoad_Util(getActivity());
            downLoad_util.downloadAPK("https://downv6.qq.com/qqweb/QQ_1/android_apk/Android_8.9.73_64.apk", (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE));
            downLoad_beanObservableArrayList.add(downLoad_util.getDownLoad_bean());

            DownLoad_Util downLoad_util1=new DownLoad_Util(getActivity());
            downLoad_util1.downloadAPK("https://dl.hdslb.com/mobile/latest/iBiliPlayer-html5_app_bili.apk",(DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE));
            downLoad_beanObservableArrayList.add(downLoad_util1.getDownLoad_bean());
    }



    @Override
    protected void onDataLazyLoad() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
