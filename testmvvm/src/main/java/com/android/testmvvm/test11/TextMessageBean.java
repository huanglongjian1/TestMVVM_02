package com.android.testmvvm.test11;

public class TextMessageBean implements BaseMessage {
    String content;
    public String imagurl;

    public TextMessageBean(String content) {
        this.content = content;
        this.imagurl = "https://img2.baidu.com/it/u=3323311628,2330835932&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=666";
    }

    public String getImagurl() {
        return imagurl;
    }

    public BaseMessage setImagurl(String imagurl) {
        this.imagurl =imagurl;
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getType() {
        return BaseMessage.TextMessage;
    }
}
