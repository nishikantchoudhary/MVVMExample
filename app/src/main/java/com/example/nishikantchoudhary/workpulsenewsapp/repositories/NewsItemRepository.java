package com.example.nishikantchoudhary.workpulsenewsapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.nishikantchoudhary.workpulsenewsapp.daos.NewsItemDAO;
import com.example.nishikantchoudhary.workpulsenewsapp.databases.NewsItemDatabase;
import com.example.nishikantchoudhary.workpulsenewsapp.models.NewsItem;
import com.example.nishikantchoudhary.workpulsenewsapp.repositories.helpers.GlobalRepositoryHelper;
import com.example.nishikantchoudhary.workpulsenewsapp.repositories.helpers.LocalRepositoryHelper;

import java.util.List;

public class NewsItemRepository {

    private LiveData<List<NewsItem>> nNewsItemList;

    private NewsItemDAO mNewsItemDAO;

    private Application mApplication;

    public NewsItemRepository(Application application) {
        mApplication = application;
        NewsItemDatabase db = NewsItemDatabase.getDatabase(mApplication);
        mNewsItemDAO = db.newsItemDAO();
        nNewsItemList = mNewsItemDAO.getAllLatestNews();
    }

    public LiveData<List<NewsItem>> getAllLatestNews(){
        return nNewsItemList;
    }

    public void saveNewsItem(NewsItem newsItem){
        LocalRepositoryHelper.saveNewsInLocalDB(mNewsItemDAO, newsItem);
    }

    public void syncLatestNews(){
        new GlobalRepositoryHelper().syncLatestNews(mNewsItemDAO);
    }
}
