package com.example.nishikantchoudhary.workpulsenewsapp.apis;

import com.example.nishikantchoudhary.workpulsenewsapp.models.ArticleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface INewsApi {

    @GET("v0/topstories.json?print=pretty")
    Call<List<Integer>> getTopStories();
    @GET("v0/item/{articleid}.json?print=pretty")
    Call<ArticleResponse> getArticle(@Path("articleid") int id);
}
