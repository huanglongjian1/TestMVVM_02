package com.android.test2mvvm.test6.test_fragment3.six.adapter;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.DownloadBinding;
import com.android.test2mvvm.test5.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test6.test_fragment3.six.DownLoad_Util;
import com.android.test2mvvm.test6.test_fragment3.six.downbean.DownLoad_Bean;

public class DownLoad_Adapter extends ObservableArrayList_BaseRecyclerViewAdapter<DownLoad_Bean, DownloadBinding> {
    private Context mContext;

    public DownLoad_Adapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.download;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        DownLoad_Bean downLoad_bean = data_list.get(position);
//        ProgressBar progressBar = holder.itemView.findViewById(R.id.test6_fragment5_progressbar);
//        progressBar.setProgress(downLoad_bean.getProgress());
//        TextView down = holder.itemView.findViewById(R.id.test6_fragment_down);
//        down.setText(downLoad_bean.getDownload());
//        TextView pro_tv = holder.itemView.findViewById(R.id.test6_fragment_pro_tv);
//        pro_tv.setText(downLoad_bean.getUrl());
        TextView textViewCancel = holder.itemView.findViewById(R.id.test6_fragment_cancel);
        textViewCancel.setText(downLoad_bean.isCancel() == true ? "下载" : "取消");
        holder.getBinding().setVariable(BR.bean, downLoad_bean);
        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoad_bean.setCancel(!downLoad_bean.isCancel());
                textViewCancel.setText(downLoad_bean.isCancel() == true ? "下载" : "取消");
                if (downLoad_bean.isCancel()) {
                    downloadManager.remove(downLoad_bean.getId());
                } else {
                    DownLoad_Util downLoad_util = new DownLoad_Util((Activity) mContext);
                    downLoad_util.downloadAPK(downLoad_bean.getUrl(), downloadManager);
                    DownLoad_Bean downLoad_bean1 = downLoad_util.getDownLoad_bean();
                    data_list.remove(downLoad_bean);
                    data_list.add(position,downLoad_bean1);
                }
            }
        });


        holder.getBinding().executePendingBindings();
    }
}
