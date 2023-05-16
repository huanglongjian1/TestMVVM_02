package com.android.testmvvm.test11;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.android.testmvvm.BR;
import com.android.testmvvm.R;

import java.util.List;

//这个是返回创造ViewHolder
//一个适配器，ViewHolder里面返回的一个ViewDataBinding
//onCreateViewHolder可以根据传入不同的类型，去形成不同的ViewDataBinding-->有不同的item布局了。
//onBindViewHolder时需要根据不同的类型去设置不同的值即可。
public class jvvm_recycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseMessage> list = null;
    private Context context = null;
    private int gviewType;
    public jvvm_recycleAdapter(Context cont, List<BaseMessage> li) {
        this.list = li;
        this.context = cont;
    }

    public List<BaseMessage> getList() {
        return list;
    }

    public void setList(List<BaseMessage> list) {
        this.list = list;
    }

    @NonNull
    @Override
    //根据传入的不同List显示不同的DataBinding
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;
        System.out.println("啥玩意:"+viewType);
        gviewType = viewType;
        switch (viewType){
            case BaseMessage.TextMessage:
                binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.test11_recyclerview_textmessagebean_item,parent,false);
                break;
            case BaseMessage.ButtonMessage:
                binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.test11_recyclerview_buttonmessagebean_item2,parent,false);
                break;
        }
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (gviewType){
            case BaseMessage.TextMessage:
                ((MyViewHolder)holder).getBinding().setVariable(BR.TextMessageBean,list.get(position));
                break;
            case BaseMessage.ButtonMessage:
                System.out.println("测试一下");
                ((MyViewHolder)holder).getBinding().setVariable(BR.ButtonMessageBean,list.get(position));
                break;
        }

        ((MyViewHolder)holder).getBinding().executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding binding = null;
        public MyViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public ViewDataBinding getBinding(){
            return binding;
        }
    }

}
