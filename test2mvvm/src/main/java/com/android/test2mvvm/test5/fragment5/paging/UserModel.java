package com.android.test2mvvm.test5.fragment5.paging;

import java.util.List;

public class UserModel {
    public int code;
    public String msg;
    public UserList data;

    public static class UserList{
        public int total;
        public List<UserInfo> userList;
    }
}
