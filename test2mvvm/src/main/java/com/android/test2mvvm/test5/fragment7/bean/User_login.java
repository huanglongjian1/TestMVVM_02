package com.android.test2mvvm.test5.fragment7.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.test2mvvm.BR;


/**
 * 用户
 *
 * @author llw
 */
public class User_login extends BaseObservable {

    public String account;
    public String pwd;

    @Bindable
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        if (this.account!=null&&
                this.account.equals(account)) return;
        this.account = account;
        notifyPropertyChanged(BR.account);//只通知改变的参数
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        if (this.pwd!=null &&
                this.pwd.equals(pwd)) return;
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);//只通知改变的参数
    }

    public User_login(String account, String pwd) {
        this.account = account;
        this.pwd = pwd;
    }

    public User_login() {
    }
}
