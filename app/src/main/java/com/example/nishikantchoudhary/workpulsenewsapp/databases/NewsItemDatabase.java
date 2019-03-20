package com.example.nishikantchoudhary.workpulsenewsapp.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.nishikantchoudhary.workpulsenewsapp.daos.NewsItemDAO;
import com.example.nishikantchoudhary.workpulsenewsapp.models.NewsItem;

@Database(entities = {NewsItem.class}, version = 2)
public abstract class NewsItemDatabase extends RoomDatabase {

    public abstract NewsItemDAO newsItemDAO();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile NewsItemDatabase INSTANCE;

    public static NewsItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsItemDatabase.class, "news_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
