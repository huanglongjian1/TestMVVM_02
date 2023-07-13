package com.android.test2mvvm.test2.bean.huochepiao_12306;

import java.util.List;

public class ValidateMessagesBean {

    /**
     * validateMessagesShowId : _validatorMessage
     * status : true
     * httpstatus : 200
     * data : {"uamflag":"1","message":"非法请求"}
     * messages : []
     * validateMessages : {}
     */

    private String validateMessagesShowId;
    private boolean status;
    private int httpstatus;
    private DataBean data;
    private ValidateMessagesBean validateMessages;
    private List<?> messages;

    public String getValidateMessagesShowId() {
        return validateMessagesShowId;
    }

    public void setValidateMessagesShowId(String validateMessagesShowId) {
        this.validateMessagesShowId = validateMessagesShowId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHttpstatus() {
        return httpstatus;
    }

    public void setHttpstatus(int httpstatus) {
        this.httpstatus = httpstatus;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ValidateMessagesBean getValidateMessages() {
        return validateMessages;
    }

    public void setValidateMessages(ValidateMessagesBean validateMessages) {
        this.validateMessages = validateMessages;
    }

    public List<?> getMessages() {
        return messages;
    }

    public void setMessages(List<?> messages) {
        this.messages = messages;
    }
}
