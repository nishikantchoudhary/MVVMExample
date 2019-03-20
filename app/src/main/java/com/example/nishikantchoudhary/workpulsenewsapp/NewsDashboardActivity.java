package com.example.nishikantchoudhary.workpulsenewsapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nishikantchoudhary.workpulsenewsapp.adapters.NewsAdapter;
import com.example.nishikantchoudhary.workpulsenewsapp.models.NewsItem;
import com.example.nishikantchoudhary.workpulsenewsapp.viewModels.NewsItemViewModel;

import java.util.List;

public class NewsDashboardActivity extends AppCompatActivity {

    /**
     * Instance of Recycler view to show news.
     */
    private RecyclerView mNewsRecyclerView;

    /**
     * Instance of News adapter.
     */
    private NewsAdapter mNewsAdapter;

    /**
     * Instance of ViewModel.
     */
    private NewsItemViewModel mNewsItemViewModel;

    /**
     * Instance of SwipeRefreshLayout to update news.
     */
    private SwipeRefreshLayout mSwipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_dashboard);
        initViews();
    }

    /**
     * Initialize views.
     */
    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);
        mSwipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        mSwipeContainer.setRefreshing(true);
        mNewsRecyclerView = findViewById(R.id.recyclerview);
        mNewsAdapter = new NewsAdapter(this);
        mNewsRecyclerView.setAdapter(mNewsAdapter);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNewsItemViewModel.getAllLatestNews().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable final List<NewsItem> newsItems) {
                mNewsAdapter.setNewsItems(newsItems);
                mNewsAdapter.notifyDataSetChanged();
                mSwipeContainer.setRefreshing(false);
            }
        });

        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mNewsItemViewModel.syncLatestNews();
            }
        });

        mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

}
