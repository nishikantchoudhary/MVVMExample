package com.example.nishikantchoudhary.workpulsenewsapp;

import android.arch.lifecycle.LiveData;

import com.example.nishikantchoudhary.workpulsenewsapp.models.NewsItem;
import com.example.nishikantchoudhary.workpulsenewsapp.repositories.NewsItemRepository;
import com.example.nishikantchoudhary.workpulsenewsapp.viewModels.NewsItemViewModel;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NewsItemViewModelTest {
    @Test
    public void addition_isCorrect() {
//        NewsItemRepository newsItemRepository = Mockito.mock(NewsItemRepository.class);
//        NewsItemViewModel newsItemViewModel = new Counter
//        List<NewsItem> newsItems  = new ArrayList<>();
//        when(newsItemRepository.getAllLatestNews()).then(LiveData);
        assertEquals(4, 2 + 2);
    }
}