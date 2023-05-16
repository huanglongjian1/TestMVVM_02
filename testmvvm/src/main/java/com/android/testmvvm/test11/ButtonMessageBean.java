package com.android.testmvvm.test11;

public class ButtonMessageBean implements BaseMessage{
    String content;

    public ButtonMessageBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getType() {
        return BaseMessage.ButtonMessage;
    }
}
