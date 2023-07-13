package com.android.test2mvvm.test5.fragment3.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;

public class Test_Fragment extends Fragment {

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<Student> students;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, null);
        initData();
        recyclerView = (RecyclerView) view.findViewById(R.id.test_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new MyAdapter();
        adapter.setData((ArrayList<Student>) students);
        recyclerView.setAdapter(adapter);
        TextView textView = view.findViewById(R.id.test_fragment_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(System.currentTimeMillis()+"");
                change(v);
                Loge.e(System.currentTimeMillis()+"");
            }
        });
        TextView textView1 = view.findViewById(R.id.test_fragment_reset_tv);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(System.currentTimeMillis()+"");
                change_nomal(v);
                Loge.e(System.currentTimeMillis()+"");
            }
        });

        return view;
    }

    public void change(View view) {
        students.set(1, new Student("Fndroid", 2));
        students.add(new Student("Jason", 8));
        Student s2 = students.get(2);
        students.remove(2);
        students.add(s2);

        ArrayList<Student> old_students = adapter.getData();
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MyCallback(old_students, (ArrayList<Student>) students), true);
        result.dispatchUpdatesTo(adapter);
        adapter.setData((ArrayList<Student>) students);

    }
    public void change_nomal(View view){
        students.set(1, new Student("Fndroid", 2));
        students.add(new Student("Jason", 8));
        Student s2 = students.get(2);
        students.remove(2);
        students.add(s2);

        adapter.setData((ArrayList<Student>) students);
        adapter.notifyDataSetChanged();

    }


    private void initData() {
        students = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Student student = new Student("AAAAA" + i, i);
            students.add(i, student);
        }
    }
}
