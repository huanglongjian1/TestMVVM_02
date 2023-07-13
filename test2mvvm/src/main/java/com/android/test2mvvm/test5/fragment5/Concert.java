package com.android.test2mvvm.test5.fragment5;

import androidx.annotation.Nullable;

public class Concert {
    String author;
    String content;
    String title;

    public Concert(String author, String content, String title) {
        this.author = author;
        this.content = content;
        this.title = title;
    }

    public Concert() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Concert concert = (Concert) obj;
        return this.getContent() == concert.getContent() &&
                this.getAuthor() == concert.getAuthor();
    }
}
