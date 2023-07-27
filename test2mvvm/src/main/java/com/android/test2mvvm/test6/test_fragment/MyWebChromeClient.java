package com.android.test2mvvm.test6.test_fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.android.test2mvvm.util.Loge;

public class MyWebChromeClient extends WebChromeClient {
    Context mContext;

    public MyWebChromeClient(Context context) {
        super();
        mContext = context;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示:看到这个，说明Java成功重写了Js的Alert方法");
        builder.setMessage(message);//这个message就是alert传递过来的值
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Loge.e("点击了确定");
                result.confirm();
            }
        });
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Loge.e("点击了取消");
                result.cancel();
            }
        });
        builder.show();
        //自己处理
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示:" +
                "看到这个，说明Java成功重写了Js的Confirm方法");
        builder.setMessage(message);//这个message就是alert传递过来的值
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //处理确定按钮了，且通过jsresult传递，告诉js点击的是确定按钮
                result.confirm();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //处理取消按钮，且通过jsresult传递，告诉js点击的是取消按钮
                result.cancel();

            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //防止用户点击对话框外围，再次点击按钮页面无反应
                result.cancel();
            }
        });
        builder.show();
        //自己处理
        return true;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示:看到这个，说明Java成功重写了Js的Prompt方法");
        builder.setMessage(message);//这个message就是alert传递过来的值
        //添加一个EditText
        final EditText editText = new EditText(mContext);
        editText.setText(defaultValue);//这个就是prompt 输入框的默认值
        //添加到对话框
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取edittext的新输入的值
                String newValue = editText.getText().toString().trim();
                //处理确定按钮了，且过jsresult传递，告诉js点击的是确定按钮(参数就是输入框新输入的值，我们需要回传到js中)
                result.confirm(newValue);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //处理取消按钮，且过jsresult传递，告诉js点击的是取消按钮
                result.cancel();

            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //防止用户点击对话框外围，再次点击按钮页面无反应
                result.cancel();
            }
        });
        builder.getClass();
        builder.show();
        //自己处理
        return true;
    }

}
