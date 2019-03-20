package com.example.nishikantchoudhary.workpulsenewsapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "news_table")
public class NewsItem {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "itemId")
    public int itemId;

    @NonNull
    @ColumnInfo(name = "headline")
    private String mHeadLine;

    @NonNull
    @ColumnInfo(name = "description")
    private String mDescription;

    @NonNull
    @ColumnInfo(name = "score")
    private Integer mScore;

    @NonNull
    @ColumnInfo(name = "isRead")
    private boolean mIsRead = false;

    public NewsItem(@NonNull String headLine, String description, Integer score) {
        this.mHeadLine = headLine;
        this.mDescription = description;
        this.mScore = score;
        itemId = 0;
    }

    @NonNull
    public String getHeadLine() {
        return this.mHeadLine;
    }

    @NonNull
    public String getDescription(){
        return mDescription;
    }

    public int getItemId(){
        return itemId;
    }

    @NonNull
    public Integer getScore(){
        return mScore;
    }

    public boolean getIsRead(){
        return mIsRead;
    }

    public void setIsRead(boolean isRead){
        mIsRead = true;
    }
}
