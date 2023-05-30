package com.android.test2mvvm.test5.fragment7.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.android.test2mvvm.BR;

/**
 * 用户
 *
 * @author llw
 */
@Entity(tableName = "user")
public class User extends BaseObservable {

    @PrimaryKey
    private int uid;
    private String account;
    private String pwd;
    @Ignore
    private String confirmPwd;
    private String nickname;
    private String introduction;
    private String avatar;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        if (this.uid == uid) return;
        this.uid = uid;
    }

    @Bindable
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        if (this.account != null && this.account.equals(account)) return;
        this.account = account;
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        if (this.pwd != null &&
                this.pwd.equals(pwd)) return;
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
    }

    @Bindable
    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        if (this.confirmPwd != null && this.confirmPwd.equals(confirmPwd)) return;
        this.confirmPwd = confirmPwd;
        notifyPropertyChanged(BR.confirmPwd);
    }

    @Bindable
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        if (this.nickname != null &&
                this.nickname.equals(nickname)) return;
        this.nickname = nickname;
        notifyPropertyChanged(BR.nickname);
    }

    @Bindable
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        if (this.introduction != null &&
                this.introduction.equals(introduction)) return;
        this.introduction = introduction;
        notifyPropertyChanged(BR.introduction);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        if (this.avatar != null &&
                this.avatar.equals(avatar)) return;
        this.avatar = avatar;
    }

    public User() {
    }

    @Ignore
    public User(int uid, String account, String pwd, String confirmPwd, String nickname, String introduction) {
        this.uid = uid;
        this.account = account;
        this.pwd = pwd;
        this.confirmPwd = confirmPwd;
        this.nickname = nickname;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", confirmPwd='" + confirmPwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", introduction='" + introduction + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
