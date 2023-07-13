package com.android.test2mvvm.test2.bean.music_163;

public class DataBean {
    /**
     * name : 雅俗共赏
     * url : http://music.163.com/song/media/outer/url?id=411214279.mp3
     * picurl : http://p1.music.126.net/Wcs2dbukFx3TUWkRuxVCpw==/3431575794705764.jpg
     * artistsname : 许嵩
     * avatarurl : http://p3.music.126.net/15heUABHDWTOUhNKVYHdgg==/109951163171302916.jpg
     * nickname : Uae的肉嵩包i-小雅俗242万楼主
     * content : 我们学校今天的广播放了肉嵩的雅俗共赏哟，是我点哒，刚好下午是我广播，和我一起广播的男生也喜欢许嵩耶，我们俩在广播室里跟唱啦，高兴得飞起[幽灵][爱心]一切都是最好的安排[大哭]
     */

    private String name;
    private String url;
    private String picurl;
    private String artistsname;
    private String avatarurl;
    private String nickname;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getArtistsname() {
        return artistsname;
    }

    public void setArtistsname(String artistsname) {
        this.artistsname = artistsname;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", picurl='" + picurl + '\'' +
                ", artistsname='" + artistsname + '\'' +
                ", avatarurl='" + avatarurl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
