package com.android.test2mvvm.test2.bean;

public class UploadBean {
    /**
     * id : 1756
     * name : null
     * description : 在下藤原拓海想来领教下各位的车技
     * disk : qiniu
     * path : i/2016-02-02-8d469bf9a3ff588eef5e76528f37dca3.jpg
     * size : 89339
     * user_id : 1
     * created_at : 2016-02-02 23:28:55
     * updated_at : 2019-07-08 21:22:33
     * uploadable_id : null
     * uploadable_type : null
     * weibo_url : https://ws2.sinaimg.cn/large/54d358dbly1fvbwjlmn0nj20fk0fk76f.jpg
     * smms : {"width":560,"height":560,"filename":"2016-02-02-8d469bf9a3ff588eef5e76528f37dca3.jpg","storename":"5d234399c16ae84900.jpg","size":89341,"path":"/2019/07/08/5d234399c16ae84900.jpg","hash":"uyGW6BgV9f7zebd","timestamp":1562592153,"ip":"140.143.185.234","url":"https://i.loli.net/2019/07/08/5d234399c16ae84900.jpg","delete":"https://sm.ms/delete/uyGW6BgV9f7zebd"}
     * url : https://i.loli.net/2019/07/08/5d234399c16ae84900.jpg
     */

    private int id;
    private Object name;
    private String description;
    private String disk;
    private String path;
    private int size;
    private int user_id;
    private String created_at;
    private String updated_at;
    private Object uploadable_id;
    private Object uploadable_type;
    private String weibo_url;
    private SmmsBean smms;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Object getUploadable_id() {
        return uploadable_id;
    }

    public void setUploadable_id(Object uploadable_id) {
        this.uploadable_id = uploadable_id;
    }

    public Object getUploadable_type() {
        return uploadable_type;
    }

    public void setUploadable_type(Object uploadable_type) {
        this.uploadable_type = uploadable_type;
    }

    public String getWeibo_url() {
        return weibo_url;
    }

    public void setWeibo_url(String weibo_url) {
        this.weibo_url = weibo_url;
    }

    public SmmsBean getSmms() {
        return smms;
    }

    public void setSmms(SmmsBean smms) {
        this.smms = smms;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
