package com.android.test2mvvm.test2.bean.music_163;

public class Music_bean {

    /**
     * code : 1
     * data : {"name":"雅俗共赏","url":"http://music.163.com/song/media/outer/url?id=411214279.mp3","picurl":"http://p1.music.126.net/Wcs2dbukFx3TUWkRuxVCpw==/3431575794705764.jpg","artistsname":"许嵩","avatarurl":"http://p3.music.126.net/15heUABHDWTOUhNKVYHdgg==/109951163171302916.jpg","nickname":"Uae的肉嵩包i-小雅俗242万楼主","content":"我们学校今天的广播放了肉嵩的雅俗共赏哟，是我点哒，刚好下午是我广播，和我一起广播的男生也喜欢许嵩耶，我们俩在广播室里跟唱啦，高兴得飞起[幽灵][爱心]一切都是最好的安排[大哭]"}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Music_bean{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
