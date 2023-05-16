package com.android.testmvvm.test8.item;

public class Item {
    String name;

    public Item(String name, int age) {
        this.name = name;
        this.age = age;
    }

    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
