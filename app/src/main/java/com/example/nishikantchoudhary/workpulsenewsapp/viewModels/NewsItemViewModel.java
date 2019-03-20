package com.example.nishikantchoudhary.workpulsenewsapp.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.nishikantchoudhary.workpulsenewsapp.daos.NewsItemDAO;
import com.example.nishikantchoudhary.workpulsenewsapp.models.NewsItem;
import com.example.nishikantchoudhary.workpulsenewsapp.repositories.NewsItemRepository;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private NewsItemRepository mRepository;
    private LiveData<List<NewsItem>> mNewsItemList;

    public NewsItemViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NewsItemRepository(application);
        mNewsItemList = mRepository.getAllLatestNews();
    }

    public LiveData<List<NewsItem>> getAllLatestNews(){
        return mNewsItemList;
    }

    public void saveNewsItem(NewsItem newsItem){
        mRepository.saveNewsItem(newsItem);
    }

    public void syncLatestNews(){
        mRepository.syncLatestNews();
    }
}
