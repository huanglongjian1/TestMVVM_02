package com.android.test2mvvm.test5.fragment2;

import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.util.Loge;

public class ListFactory {

    public static ObservableList.OnListChangedCallback getListChangedCallback(RecyclerView.Adapter adapter) {

        return new ObservableList.OnListChangedCallback() {
            @Override
            public void onChanged(ObservableList sender) {
                adapter.notifyDataSetChanged();
                Loge.e("onChanged");
            }

            @Override
            public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeChanged(positionStart, itemCount);
                Loge.e("onItemRangeChanged");
            }

            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeInserted(positionStart, itemCount);
                adapter.notifyItemRangeChanged(positionStart, adapter.getItemCount() - positionStart);
                Loge.e("onItemRangeInserted:"+sender.toString());
            }

            @Override
            public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
                if (itemCount == 1) {
                    adapter.notifyItemMoved(fromPosition, toPosition);
                } else {
                    adapter.notifyDataSetChanged();
                }
                Loge.e("onItemRangeMoved");
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                adapter.notifyItemRemoved(positionStart);
                adapter.notifyItemRangeChanged(positionStart, adapter.getItemCount() - positionStart);
                Loge.e("onItemRangeRemoved");
            }
        };

    }

}
