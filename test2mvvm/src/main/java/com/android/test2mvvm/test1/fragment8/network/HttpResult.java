package com.android.test2mvvm.test1.fragment8.network;

public class HttpResult<T> {
    public int status;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "HttpResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
