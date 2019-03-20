package com.example.nishikantchoudhary.workpulsenewsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nishikantchoudhary.workpulsenewsapp.R;
import com.example.nishikantchoudhary.workpulsenewsapp.models.NewsItem;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    private final LayoutInflater mInflater;
    private List<NewsItem> mNewsItems;

    public NewsAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    class NewsItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNewsHeadLine;
        private final TextView tvNewsScore;
        private final TextView tvNewsDescription;

        private NewsItemViewHolder(View itemView) {
            super(itemView);
            tvNewsHeadLine = itemView.findViewById(R.id.tv_news_headline);
            tvNewsScore = itemView.findViewById(R.id.tv_news_score);
            tvNewsDescription = itemView.findViewById(R.id.tv_news_description);
        }
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.news_item_recycler_view, parent, false);
        NewsItemViewHolder newsItemViewHolder =  new NewsItemViewHolder(itemView);
        return newsItemViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        if (mNewsItems != null) {
            NewsItem current = mNewsItems.get(position);
            holder.tvNewsHeadLine.setText(current.getHeadLine());
            holder.tvNewsScore.setText(current.getScore().toString());
            holder.tvNewsDescription.setText(current.getDescription());
        }
    }

    public void setNewsItems(List<NewsItem> newsItems){
        mNewsItems = newsItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNewsItems != null)
            return mNewsItems.size();
        else return 0;
    }
}