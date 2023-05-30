package com.android.test2mvvm.test2.bean;

public class zhuangbi_bean {

    /**
     * id : 579
     * description : 在下藤原拓海想来领教下各位的车技
     * path :
     * size : 0
     * width : 560
     * height : 560
     * created_at : 2016-02-02 23:28:55
     * updated_at : 2016-02-02 23:29:04
     * user_id : 1
     * permitted_at : 2016-02-02 15:29:04
     * disk : qiniu
     * hotpoint : 231
     * channel : admin
     * upload_id : 1756
     * content :
     * provider_name :
     * image_url : https://i.loli.net/2019/07/08/5d234399c16ae84900.jpg
     * file_size : 87.25 KB
     * upload : {"id":1756,"name":null,"description":"在下藤原拓海想来领教下各位的车技","disk":"qiniu","path":"i/2016-02-02-8d469bf9a3ff588eef5e76528f37dca3.jpg","size":89339,"user_id":1,"created_at":"2016-02-02 23:28:55","updated_at":"2019-07-08 21:22:33","uploadable_id":null,"uploadable_type":null,"weibo_url":"https://ws2.sinaimg.cn/large/54d358dbly1fvbwjlmn0nj20fk0fk76f.jpg","smms":{"width":560,"height":560,"filename":"2016-02-02-8d469bf9a3ff588eef5e76528f37dca3.jpg","storename":"5d234399c16ae84900.jpg","size":89341,"path":"/2019/07/08/5d234399c16ae84900.jpg","hash":"uyGW6BgV9f7zebd","timestamp":1562592153,"ip":"140.143.185.234","url":"https://i.loli.net/2019/07/08/5d234399c16ae84900.jpg","delete":"https://sm.ms/delete/uyGW6BgV9f7zebd"},"url":"https://i.loli.net/2019/07/08/5d234399c16ae84900.jpg"}
     */

    private int id;
    private String description;
    private String path;
    private int size;
    private int width;
    private int height;
    private String created_at;
    private String updated_at;
    private int user_id;
    private String permitted_at;
    private String disk;
    private int hotpoint;
    private String channel;
    private int upload_id;
    private String content;
    private String provider_name;
    private String image_url;
    private String file_size;
    private UploadBean upload;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPermitted_at() {
        return permitted_at;
    }

    public void setPermitted_at(String permitted_at) {
        this.permitted_at = permitted_at;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public int getHotpoint() {
        return hotpoint;
    }

    public void setHotpoint(int hotpoint) {
        this.hotpoint = hotpoint;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(int upload_id) {
        this.upload_id = upload_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public UploadBean getUpload() {
        return upload;
    }

    public void setUpload(UploadBean upload) {
        this.upload = upload;
    }

    @Override
    public String toString() {
        return "zhuangbi_bean{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", width=" + width +
                ", height=" + height +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", user_id=" + user_id +
                ", permitted_at='" + permitted_at + '\'' +
                ", disk='" + disk + '\'' +
                ", hotpoint=" + hotpoint +
                ", channel='" + channel + '\'' +
                ", upload_id=" + upload_id +
                ", content='" + content + '\'' +
                ", provider_name='" + provider_name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", file_size='" + file_size + '\'' +
                ", upload=" + upload +
                '}';
    }
}
