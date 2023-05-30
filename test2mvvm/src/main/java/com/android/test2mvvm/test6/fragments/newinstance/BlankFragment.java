package com.android.test2mvvm.test6.fragments.newinstance;

import android.graphics.PixelFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test6.fragments.matrix.TouchListener;
import com.android.test2mvvm.util.Loge;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment implements View.OnTouchListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    TextView textView;
    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

    @Override
    public void onResume() {
        super.onResume();
        textView = new TextView(getContext());
        TextView textView1 = getView().findViewById(R.id.blank_tv);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager windowManager = getActivity().getWindowManager();

                textView.setText("今晚喝酒");

                layoutParams.format = PixelFormat.RGBA_8888;
                layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                layoutParams.width = 300;
                layoutParams.height = 300;
                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

                layoutParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
                layoutParams.x = 30;
                layoutParams.y = 40;

                if (textView.getParent()==null){
                    windowManager.addView(textView, layoutParams);
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getWindowManager().removeView(textView);
            }
        });
        textView.setOnTouchListener(this);

        ImageView imageView=getView().findViewById(R.id.blank_imageview);
        imageView.setOnTouchListener(new TouchListener(imageView));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Loge.e("销毁视图");
    }

    private void drag(int x, int y) {
        layoutParams.x = x + textView.getLeft() + 10;
        layoutParams.y = y + textView.getTop() + 100;
        getActivity().getWindowManager().updateViewLayout(textView, layoutParams);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       if ((event.getAction() & MotionEvent.ACTION_MASK) ==MotionEvent.ACTION_MOVE){
           int x= (int) event.getX();
           int y= (int) event.getY();
           drag(x,y);
       }

        return false;
    }
}