package com.android.test2mvvm.test5.fragment3.differ;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test5.fragment3.Loading;
import com.android.test2mvvm.util.Loge;

import java.util.List;
import java.util.Random;

public class Differ_Fragment extends Fragment {
    List<Teacher> teacherList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, null);
        init_data();
        init_view(view);
        return view;
    }

    private void init_data() {
        teacherList = new ObservableArrayList<>();
        for (int i = 0; i < 50; i++) {
            Teacher teacher = new Teacher(i, "teacher" + i);
            teacherList.add(teacher);
        }
    }

    private void init_view(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.test_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RV_Adapter rv_adapter = new RV_Adapter(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        rv_adapter.setData_list((ObservableArrayList<Teacher>) teacherList);
        recyclerView.setAdapter(rv_adapter);
        linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        Loading loading = new Loading() {
            @Override
            public void onLoadMore() {
                Loge.e("到底了，加载更多");
            }

            @Override
            public void refresh() {
                Loge.e("刷新数据");
            }
        };

        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                int index = linearLayoutManager.getPosition(view);
                Loge.e(index + "-" + rv_adapter.getItemCount());
                Loge.e(recyclerView.getScrollState()+"---");
                if (index == rv_adapter.getItemCount() - 1) {
                    if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING) {
                        loading.onLoadMore();
                    }

                } else if (index == 0) {
                    if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                        loading.refresh();
                    }
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Loge.e(recyclerView.getChildCount()+"-child");
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


        TextView textView = view.findViewById(R.id.test_fragment_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObservableArrayList<Teacher> observableArrayList = new ObservableArrayList<>();
                for (int i = 0; i < 5; i++) {
                    Teacher teacher = new Teacher(i, "teacher" + i);
                    observableArrayList.add(teacher);
                }
                rv_adapter.differ_addAll(observableArrayList);
            }
        });
        TextView textView1 = view.findViewById(R.id.test_fragment_reset_tv);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = new Random().nextInt(rv_adapter.getItemCount() - 1);
                rv_adapter.reset(position, new Teacher(position, "修改老师" + position));
            }
        });
    }
}
