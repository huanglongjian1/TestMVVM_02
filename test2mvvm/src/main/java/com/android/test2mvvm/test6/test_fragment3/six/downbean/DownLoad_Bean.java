package com.android.test2mvvm.test6.test_fragment3.six.downbean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.test2mvvm.BR;


public class DownLoad_Bean extends BaseObservable {
    String download;
    int progress;
    boolean isCancel;
    String url;
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void notifyPropertyChanged(int fieldId) {
        super.notifyPropertyChanged(fieldId);
        if (fieldId != BR.description) {
            notifyPropertyChanged(BR.description);
        }
    }

    @Bindable
    public String getDownload() {
        return download;
    }


    public void setDownload(String download) {
        this.download = download;
        notifyPropertyChanged(BR.download);
    }

    @Bindable
    public int getProgress() {
        return progress;
    }


    public void setProgress(int progress) {
        this.progress = progress;
        notifyPropertyChanged(BR.progress);

    }

    @Bindable
    public boolean isCancel() {
        return isCancel;
    }


    public void setCancel(boolean cancel) {
        isCancel = cancel;
        notifyPropertyChanged(BR.cancel);
    }

    @Bindable
    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }
}
