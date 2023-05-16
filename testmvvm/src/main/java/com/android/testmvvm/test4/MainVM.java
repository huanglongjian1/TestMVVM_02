package com.android.testmvvm.test4;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test4ActivityLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class MainVM extends AndroidViewModel {
    private static Test4ActivityLayoutBinding binding;
    @SuppressLint("StaticFieldLeak")
    private static Test4_Activity mainActivity;
    public static MutableLiveData<String> text = new MutableLiveData<>();

    public MainVM(@NonNull Application application) {
        super(application);
        text.setValue("这是初始值");
        imgdir.setValue("https://cache.4ce.cn/star3/origin/e207f80d31abd663628acdbdf76cf0b7.png");
        data.setValue("null");
    }

    public void setBinding(Test4ActivityLayoutBinding binding, Test4_Activity mainActivity) {
        //把binding和mainActivity都赋值给MainVM作为静态变量备用，因为很多绑定的控件都只能用静态方法
        MainVM.binding = binding;
        MainVM.mainActivity = mainActivity;
    }

    public void click(View view) {
        Toast.makeText(mainActivity, "你点击了按钮", Toast.LENGTH_SHORT).show();
        text.setValue(String.valueOf(new Random().nextInt(1000)));
        data.setValue(String.valueOf(new Random().nextInt(100000)));
    }

    public static MutableLiveData<String> imgdir = new MutableLiveData<>();
    public static MutableLiveData<String> data = new MutableLiveData<>();
    public static int w = 500;
    public static int h = 500;

    @BindingAdapter(value = {"bindImage", "data", "layout_width", "layout_height"}, requireAll = false)
    public static void setDataImage(ImageView imageView, MutableLiveData<String> imgdir, MutableLiveData<String> data, int ww, int hh) {
        if (!imgdir.getValue().equals("")) {
            //   imageView.setImageBitmap(BitmapFactory.decodeFile(imgdir.getValue()));
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = ww;
            layoutParams.height = hh;
            imageView.setLayoutParams(layoutParams);


            Picasso.get().load(imgdir.getValue()).placeholder(R.drawable.ic_launcher_background).into(imageView);
            Log.e("--------", data.getValue());
            Log.e("========", ww + "***" + hh);
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).into(imageView);

    }

}
