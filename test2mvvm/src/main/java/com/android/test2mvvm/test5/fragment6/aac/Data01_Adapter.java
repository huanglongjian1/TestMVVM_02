package com.android.test2mvvm.test5.fragment6.aac;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment4RvItemBinding;
import com.android.test2mvvm.databinding.Test5Fragment5RvItemBinding;
import com.android.test2mvvm.test5.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.test5.fragment4.bean.Person;
import com.android.test2mvvm.util.Loge;

public class Data01_Adapter extends ObservableArrayList_BaseRecyclerViewAdapter<NewsDataVo, Test5Fragment4RvItemBinding> {

    public Data01_Adapter(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.test5_fragment4_rv_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        NewsDataVo newsDataVo = data_list.get(position);

//        if (newsDataVo != null) {
//            Test5Fragment4RvItemBinding binding = (Test5Fragment4RvItemBinding) holder.getBinding();
//            binding.test5Fragment4TvName.setText("-------");
//            binding.test5Fragment4TvName.setTextColor(Color.RED);
//            Loge.e(newsDataVo.toString());
//        }
        com.android.test2mvvm.test5.fragment4.bean.Person person = new Person();
        person.setName(newsDataVo.toString());
        holder.getBinding().setVariable(BR.person, person);
        holder.getBinding().executePendingBindings();

    }


}
