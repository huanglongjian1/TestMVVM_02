package com.android.test2mvvm.test1.retrofit;

public class ApiResponse<T> {

    public static final int CODE_SUCCESS = 0;
    public static final int CODE_ERROR = 1;
    public boolean success;
    public int code; //状态码
    public String message; //信息
    public T data; //数据

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.message = msg;
        this.data = null;
    }

    public ApiResponse() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
