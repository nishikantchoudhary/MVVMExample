package com.example.nishikantchoudhary.workpulsenewsapp.repositories.helpers;

import android.os.AsyncTask;

import com.example.nishikantchoudhary.workpulsenewsapp.daos.NewsItemDAO;
import com.example.nishikantchoudhary.workpulsenewsapp.models.NewsItem;

/**
 * Helper class that deals with operations related to local DB sqlite
 */
public class LocalRepositoryHelper {

    private static class SaveNewsAsyncTask extends AsyncTask<NewsItem, Void, Void> {

        private NewsItemDAO mAsyncTaskDao;

        SaveNewsAsyncTask(NewsItemDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final NewsItem... params) {
            mAsyncTaskDao.addNewsItemInLocalDB(params[0]);
            return null;
        }
    }

    public static void saveNewsInLocalDB(NewsItemDAO dao, NewsItem newsItem) {
        new SaveNewsAsyncTask(dao).execute(newsItem);
    }
}
