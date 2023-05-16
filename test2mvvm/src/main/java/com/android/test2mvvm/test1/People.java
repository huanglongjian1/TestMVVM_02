package com.android.test2mvvm.test1;

public class People {
    public String imagurl;
    public String name;
    public String age;

    public People(String imagurl, String name, String age) {
        this.imagurl = imagurl;
        this.name = name;
        this.age = age;
    }

    public String getImagurl() {
        return imagurl;
    }

    public void setImagurl(String imagurl) {
        this.imagurl = imagurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
