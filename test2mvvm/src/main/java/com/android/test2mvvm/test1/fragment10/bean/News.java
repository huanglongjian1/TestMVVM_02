package com.android.test2mvvm.test1.fragment10.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.test2mvvm.BR;

import java.util.List;

/**
 * 新闻实体类
 * */
public class News extends BaseObservable {


    /**
     * code : 200
     * msg : success
     * newslist : [{"id":"2f5ef5351c8139baecf39b97133db3d3","ctime":"2021-03-13 09:45","title":"这段东北女法官庭审视频火出圈了 也就看了100来遍","description":"","source":"中华社会","picUrl":"https://img0.utuku.imgcdc.com/300x200/news/20210313/9a6cc7ae-37f5-4bba-8e0e-fcd23e1eac54.jpg","url":"https://news.china.com/social/1007/20210313/39375399.html"}]
     */

    private Integer code;
    private String msg;
    private List<NewslistBean> newslist;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public class NewslistBean extends BaseObservable{
        /**
         * id : 2f5ef5351c8139baecf39b97133db3d3
         * ctime : 2021-03-13 09:45
         * title : 这段东北女法官庭审视频火出圈了 也就看了100来遍
         * description :
         * source : 中华社会
         * picUrl : https://img0.utuku.imgcdc.com/300x200/news/20210313/9a6cc7ae-37f5-4bba-8e0e-fcd23e1eac54.jpg
         * url : https://news.china.com/social/1007/20210313/39375399.html
         */

        private String id;
        private String ctime;
        private String title;
        private String description;
        private String source;
        private String picUrl;
        private String url;

        @Bindable
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
            notifyPropertyChanged(BR.id);
        }

        @Bindable
        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
            notifyPropertyChanged(BR.ctime);
        }

        @Bindable
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
            notifyPropertyChanged(BR.title);
        }

        @Bindable
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
            notifyPropertyChanged(BR.description);
        }

        @Bindable
        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
            notifyPropertyChanged(BR.source);
        }

        @Bindable
        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
            notifyPropertyChanged(BR.picUrl);
        }

        @Bindable
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
            notifyPropertyChanged(BR.url);
        }

        @Override
        public String toString() {
            return "NewslistBean{" +
                    "id='" + id + '\'' +
                    ", ctime='" + ctime + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", source='" + source + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

}

