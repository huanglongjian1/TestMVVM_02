package com.android.testmvvm.test6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test6RecyclerviewItemBinding;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;
    List<String> datas;

    public MyAdapter(Context context,List<String> datas) {
        mContext = context;
        this.datas =datas ;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Test6RecyclerviewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.test6_recyclerview_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        String name =datas.get(position);
        holder.getBinding().setVariable(com.android.testmvvm.BR.str,name);
        //holder.getBinding().setStr(name); //两者都可以

        //executePendingBindings()方法说明
        // When a variable or observable changes, the binding will be scheduled to change before the next frame.
        // There are times, however, when binding must be executed immediately.
        // To force execution, use the executePendingBindings() method.
        holder.getBinding().executePendingBindings();//此方法必须执行在UI线程，当绑定的数据修改时更新视图（不知道翻译的准不准）
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Test6RecyclerviewItemBinding binding;

        public MyViewHolder(Test6RecyclerviewItemBinding binding) {
            super(binding.getRoot());
            this.binding = (Test6RecyclerviewItemBinding) binding;
        }

        public Test6RecyclerviewItemBinding getBinding() {
            return binding;
        }

        public void setBinding(Test6RecyclerviewItemBinding binding) {
            this.binding = binding;
        }
    }
}
