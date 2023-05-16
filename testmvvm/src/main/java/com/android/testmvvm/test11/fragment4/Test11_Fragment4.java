package com.android.testmvvm.test11.fragment4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test11Fragment4RecyclerviewBinding;

import java.util.ArrayList;
import java.util.List;

public class Test11_Fragment4 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Test11Fragment4RecyclerviewBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.test11_fragment4_recyclerview, container, false);
        SalesNewsAdapter mAdapter = new SalesNewsAdapter(getActivity());

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mBinding.recyclerviewTest11Fragment4.setLayoutManager(mGridLayoutManager);
        mBinding.recyclerviewTest11Fragment4.setAdapter(mAdapter);
        List<SalesNewInfo> mlist = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SalesNewInfo info = new SalesNewInfo();
            info.setTitle("lala" + i);
            if (i < 2) {
                info.setBegging(true);
            } else {
                info.setBegging(false);
            }
            mlist.add(info);
        }
        mAdapter.addAll(mlist);

        mAdapter.setListener(new SalesNewsAdapter.OnItemClickListener() {
            @Override
            public void onsalesNewsClick(SalesNewInfo salesNews) {

                //   SalesDetailInfoActivity.start(SalesPromotionActivity.this);
                Toast.makeText(getActivity(), salesNews.getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        mBinding.addText11Recyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalesNewInfo salesNewInfo = new SalesNewInfo();
                salesNewInfo.setBegging(true);
                salesNewInfo.setTitle("今晚吃鸡");
                mAdapter.add(salesNewInfo);
            }
        });
        return mBinding.getRoot();
    }
}
