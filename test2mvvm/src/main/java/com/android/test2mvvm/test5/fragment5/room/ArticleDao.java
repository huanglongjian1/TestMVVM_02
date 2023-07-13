package com.android.test2mvvm.test5.fragment5.room;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticleDao {
    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<Article> article);

    /**
     * 清空数据
     */
    @Query("DELETE FROM article")
    void clear();

    /**
     * Room对Paging提供了原生支持，这里直接返回DataSource.Factory，
     * 以便LivePagedListBuilder在创建的时候使用。
     */
    @Query("SELECT * FROM article")
    DataSource.Factory<Integer, Article> getArticlesList();
    @Query("SELECT * FROM article")
    List<Article> getArticles_List();
}
