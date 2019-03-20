package com.example.nishikantchoudhary.workpulsenewsapp.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.nishikantchoudhary.workpulsenewsapp.models.NewsItem;

import java.util.List;

@Dao
public interface NewsItemDAO {

    @Query("Select * from news_table order by score")
    LiveData<List<NewsItem>> getAllLatestNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNewsItemInLocalDB(NewsItem newsItem);

   @Query("DELETE FROM news_table")
    void deleteAll();
}
