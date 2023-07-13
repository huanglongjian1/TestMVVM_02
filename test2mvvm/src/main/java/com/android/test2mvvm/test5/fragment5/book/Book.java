package com.android.test2mvvm.test5.fragment5.book;

public class Book {
    private String title;
    private String content;
    private String author;
    public String img;


    public Book() {

    }


    public Book(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
