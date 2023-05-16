package com.android.test2mvvm.test1.fragment3;

public class BookRatingUtils {
    public static String getRatingString(int rat){
        switch (rat){
            case 0:
                return "非常不好看";
            case 1:
                return "不好看";
            case 2:
                return "有一点不好看";
            case 3:
                return "有一点好看";
            case 4:
                return "好看";
            case 5:
                return "非常好看";
        }
        return "";
    }
}