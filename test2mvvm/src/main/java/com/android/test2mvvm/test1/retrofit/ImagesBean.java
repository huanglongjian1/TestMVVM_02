package com.android.test2mvvm.test1.retrofit;

import java.util.List;

public class ImagesBean {
    /**
     * startdate : 20230528
     * fullstartdate : 202305281600
     * enddate : 20230529
     * url : /th?id=OHR.Antilles_ZH-CN8267285876_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp
     * urlbase : /th?id=OHR.Antilles_ZH-CN8267285876
     * copyright : 萨克马兰大湾，瓜德罗普岛国家公园，小安的列斯群岛 (© Hemis/Alamy)
     * copyrightlink : https://www.bing.com/search?q=%E7%93%9C%E5%BE%B7%E7%BD%97%E6%99%AE%E5%B2%9B&form=hpcapt&mkt=zh-cn
     * title : 人间天堂
     * quiz : /search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20230528_Antilles%22&FORM=HPQUIZ
     * wp : true
     * hsh : eb4c793852f388c10e7f44c2e9f6777f
     * drk : 1
     * top : 1
     * bot : 1
     * hs : []
     */

    private String startdate;
    private String fullstartdate;
    private String enddate;
    private String url;
    private String urlbase;
    private String copyright;
    private String copyrightlink;
    private String title;
    private String quiz;
    private boolean wp;
    private String hsh;
    private int drk;
    private int top;
    private int bot;
    private List<?> hs;

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getFullstartdate() {
        return fullstartdate;
    }

    public void setFullstartdate(String fullstartdate) {
        this.fullstartdate = fullstartdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlbase() {
        return urlbase;
    }

    public void setUrlbase(String urlbase) {
        this.urlbase = urlbase;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCopyrightlink() {
        return copyrightlink;
    }

    public void setCopyrightlink(String copyrightlink) {
        this.copyrightlink = copyrightlink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public boolean isWp() {
        return wp;
    }

    public void setWp(boolean wp) {
        this.wp = wp;
    }

    public String getHsh() {
        return hsh;
    }

    public void setHsh(String hsh) {
        this.hsh = hsh;
    }

    public int getDrk() {
        return drk;
    }

    public void setDrk(int drk) {
        this.drk = drk;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBot() {
        return bot;
    }

    public void setBot(int bot) {
        this.bot = bot;
    }

    public List<?> getHs() {
        return hs;
    }

    public void setHs(List<?> hs) {
        this.hs = hs;
    }
}
