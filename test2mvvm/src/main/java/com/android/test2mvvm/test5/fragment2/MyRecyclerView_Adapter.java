package com.android.test2mvvm.test5.fragment2;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment2RvItemBinding;
import com.android.test2mvvm.test5.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;
import com.android.test2mvvm.util.Loge;

import java.util.List;
import java.util.Random;

public class MyRecyclerView_Adapter<T> extends ObservableArrayList_BaseRecyclerViewAdapter<String, Test5Fragment2RvItemBinding> {
    ObservableArrayList<T> observableArrayList;

    public MyRecyclerView_Adapter(Context context, ObservableArrayList<T> observableArrayList) {
        super(context);
        this.observableArrayList = observableArrayList;
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.test5_fragment2_rv_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        Test5Fragment2RvItemBinding binding = (Test5Fragment2RvItemBinding) holder.getBinding();
        binding.test5Fragment5AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    observableArrayList.add(position, observableArrayList.get(position));
                Loge.e(position + "-----");
                add(position, String.valueOf(position) + "位置");
            }
        });
        binding.test5Fragment5DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(position + "-----");
                delete(position);
            }
        });
        binding.test5Fragment5ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(position + "-----");
                reset(position, position + ":被修改了" + new Random().nextInt(1000));
            }
        });
        binding.test5Fragment5RvTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(position, "image");
            }
        });
        String s = data_list.get(position);
        holder.getBinding().setVariable(BR.sssss, s);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<Test5Fragment2RvItemBinding> holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            String payload = (String) payloads.get(0);
            if (payload.trim().equals("image")) {
                ImageView imageView = holder.itemView.findViewById(R.id.test5_fragment2_payload_image);
                imageView.setImageResource(R.drawable.ic_launcher_background);
            }
            Loge.e("局部刷新:" + payload);
        }
    }
}
