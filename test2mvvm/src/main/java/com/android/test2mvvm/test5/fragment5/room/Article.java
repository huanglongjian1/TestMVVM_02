package com.android.test2mvvm.test5.fragment5.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Article {
    @PrimaryKey()
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    public String author;
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    public String desc;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    public String title;

    public Article(int id, String author, String desc, String title) {
        this.id = id;
        this.author = author;
        this.desc = desc;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", desc='" + desc + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
