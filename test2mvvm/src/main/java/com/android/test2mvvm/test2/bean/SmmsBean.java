package com.android.test2mvvm.test2.bean;

public class SmmsBean {
    /**
     * width : 560
     * height : 560
     * filename : 2016-02-02-8d469bf9a3ff588eef5e76528f37dca3.jpg
     * storename : 5d234399c16ae84900.jpg
     * size : 89341
     * path : /2019/07/08/5d234399c16ae84900.jpg
     * hash : uyGW6BgV9f7zebd
     * timestamp : 1562592153
     * ip : 140.143.185.234
     * url : https://i.loli.net/2019/07/08/5d234399c16ae84900.jpg
     * delete : https://sm.ms/delete/uyGW6BgV9f7zebd
     */

    private int width;
    private int height;
    private String filename;
    private String storename;
    private int size;
    private String path;
    private String hash;
    private int timestamp;
    private String ip;
    private String url;
    private String delete;

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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "SmmsBean{" +
                "width=" + width +
                ", height=" + height +
                ", filename='" + filename + '\'' +
                ", storename='" + storename + '\'' +
                ", size=" + size +
                ", path='" + path + '\'' +
                ", hash='" + hash + '\'' +
                ", timestamp=" + timestamp +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", delete='" + delete + '\'' +
                '}';
    }
}
