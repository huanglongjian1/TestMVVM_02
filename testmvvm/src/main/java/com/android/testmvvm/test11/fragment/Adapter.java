package com.android.testmvvm.test11.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.android.testmvvm.BR;
import com.android.testmvvm.R;

import java.util.List;
import java.util.Objects;

public class Adapter extends BaseAdapter {
    Context context;
    public List<Bean> data;
    public Adapter(Context context, List<Bean> objects){
        super();
        this.context=context;
        data=objects;
    }
    @Override
    public int getCount() {
        return Objects.requireNonNull(data).size();
    }
    @Override
    public Object getItem(int position) {
        return Objects.requireNonNull(data).get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        @SuppressLint("ViewHolder") ViewDataBinding binding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.test11_fragment_listview_item, parent, false);
        binding.setVariable(BR.bean, Objects.requireNonNull(data).get(position));
        return binding.getRoot();
    }
}
