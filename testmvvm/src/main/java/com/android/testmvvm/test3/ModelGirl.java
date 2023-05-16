package com.android.testmvvm.test3;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class ModelGirl extends BaseObservable {
    private boolean success;
    private String imgurl;
    private Info info;


    @Override
    public String toString() {
        return "MobileGirl{" +
                "success=" + success +
                ", imgurl='" + imgurl + '\'' +
                ", info=" + info.toString() +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Bindable
    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
        notifyPropertyChanged(BR.imgurl);
    }

    @Bindable
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
        notifyPropertyChanged(com.android.testmvvm.BR.info);
    }

    public static class Info {
        private int width;
        private int height;
        private String type;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "width=" + width +
                    ", height=" + height +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

}