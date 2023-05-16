package com.android.test2mvvm.test1.fragment4;

public class Book {
    String author;
    String title;
    String img;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
