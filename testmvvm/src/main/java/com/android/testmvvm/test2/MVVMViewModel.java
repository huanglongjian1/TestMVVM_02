package com.android.testmvvm.test2;

import android.app.Application;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.testmvvm.BR;


public class MVVMViewModel extends BaseObservable {
    private MVVMModel mvvmModel;
    //EditText数据
    private String userInput;
    //网络返回数据
    private String result;

    public MVVMViewModel(Application application){
        mvvmModel = new MVVMModel();
    }

    public void getData(View view){
        mvvmModel.getHttpData(userInput, new MCallback() {
            @Override
            public void onSuccess(String text) {
                setResult(text);
            }

            @Override
            public void onFailed() {
                setResult("数据获取失败");
            }
        });
    }

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}