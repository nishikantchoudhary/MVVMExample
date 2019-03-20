package com.example.nishikantchoudhary.workpulsenewsapp.repositories.helpers;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.example.nishikantchoudhary.workpulsenewsapp.apiClients.NewsApiClient;
import com.example.nishikantchoudhary.workpulsenewsapp.apis.INewsApi;
import com.example.nishikantchoudhary.workpulsenewsapp.daos.NewsItemDAO;
import com.example.nishikantchoudhary.workpulsenewsapp.models.ArticleResponse;
import com.example.nishikantchoudhary.workpulsenewsapp.models.NewsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Helper class that Deals with network related operations.
 */
public class GlobalRepositoryHelper {

    private Handler mHandler;

    public GlobalRepositoryHelper() {

    }

    private void initInsertionThread() {
        HandlerThread handlerThread = new HandlerThread("NewsItemInsertionThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        mHandler = new Handler(looper);
    }

    /**
     * Fetches data from api and inserts items in local DB.
     *
     * @param newsItemDAO DAO to insert items in local DB
     */
    public void syncLatestNews(final NewsItemDAO newsItemDAO) {
        initInsertionThread();
        final INewsApi apiInterface = NewsApiClient.getClient().create(INewsApi.class);
        apiInterface.getTopStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                List<Integer> topStories = response.body();
                for (int i = 0; i < 10; i++) {
                    apiInterface.getArticle(topStories.get(i)).enqueue(new Callback<ArticleResponse>() {
                        @Override
                        public void onResponse(Call<ArticleResponse> call,
                                               final Response<ArticleResponse> response) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    newsItemDAO.addNewsItemInLocalDB(new NewsItem(response.body()
                                            .getTitle(), response.body().getUrl(), response.body
                                            ().getScore()));
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<ArticleResponse> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {

            }
        });
    }
}
