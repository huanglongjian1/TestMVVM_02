package com.android.test2mvvm.test5.fragment6.aac;

public class NewsDataVo {
    private String id;
    private String newsTitle;
    private String newsContent;
    private String newsUrl;
    private int readNum;

    @Override
    public String toString() {
        return "NewsDataVo{" +
                "id='" + id + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", newsUrl='" + newsUrl + '\'' +
                ", readNum=" + readNum +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }
}