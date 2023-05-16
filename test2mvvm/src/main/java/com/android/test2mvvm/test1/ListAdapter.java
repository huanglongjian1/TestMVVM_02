package com.android.test2mvvm.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.android.test2mvvm.databinding.Test1ActivityListviewItemBinding;

import java.util.List;

/**
 * Created by 轩志强 on 2017/7/20.
 * 第一步：写一个DataBinding 通用的Adapter（先写好item布局）
 */

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
        Test1ActivityListviewItemBinding binding = null;
        if (binding == null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(context),layouId,viewGroup,false);
        }else{
            binding = DataBindingUtil.getBinding(view);
        }
        binding.setVariable(variableId,list.get(i));
        return binding.getRoot();
    }
}