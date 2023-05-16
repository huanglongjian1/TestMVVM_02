package com.android.testmvvm.test11.fragment5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.android.testmvvm.databinding.Test11Fragment5ListviewItemBinding;

import java.util.List;

public class ListAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> list;
    private int layouId; // item布局Id
    private int variableId; // item布局中声明的对象Id

    public ListAdapter(Context context, List<T> list, int layouId, int variableId) {
        this.context = context;
        this.list = list;
        this.layouId = layouId;
        this.variableId = variableId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Test11Fragment5ListviewItemBinding binding = null;
        if (binding == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), layouId, viewGroup, false);
        } else {
            binding = DataBindingUtil.getBinding(view);
        }
        binding.setVariable(variableId, list.get(i));
        return binding.getRoot();
    }
}