package com.android.test2mvvm.test1.retrofit;

public class TooltipsBean {
    /**
     * loading : 正在加载...
     * previous : 上一个图像
     * next : 下一个图像
     * walle : 此图片不能下载用作壁纸。
     * walls : 下载今日美图。仅限用作桌面壁纸。
     */

    private String loading;
    private String previous;
    private String next;
    private String walle;
    private String walls;

    public String getLoading() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading = loading;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getWalle() {
        return walle;
    }

    public void setWalle(String walle) {
        this.walle = walle;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }
}
