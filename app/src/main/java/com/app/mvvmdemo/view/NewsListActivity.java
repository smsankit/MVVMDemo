package com.app.mvvmdemo.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.mvvmdemo.R;
import com.app.mvvmdemo.databinding.ActivityNewsListBinding;
import com.app.mvvmdemo.model.ArticlesItem;
import com.app.mvvmdemo.util.ViewModelFactory;
import com.app.mvvmdemo.view.adapter.NewsListAdapter;
import com.app.mvvmdemo.viewmodel.NewsListViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class NewsListActivity extends DaggerAppCompatActivity implements NewsListAdapter.OnItemClickListener {
    @Inject
    ViewModelFactory viewModelFactory;
    NewsListViewModel newsListViewModel;
    ActivityNewsListBinding binding;
    List<ArticlesItem> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_list);

        newsListViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel.class);
        binding.setViewmodel(newsListViewModel);

        ObserverViewModel();
        newsListViewModel.getNews();
    }

    private void ObserverViewModel() {
        newsListViewModel.getLoading().observe(this, isLoading -> {
            if (isLoading) {
                binding.progress.setVisibility(View.VISIBLE);
            } else {
                binding.progress.setVisibility(View.GONE);
            }
        });

        newsListViewModel.getNewsList().observe(this, newsResponse -> {
            newsList = newsResponse.getArticles();
            NewsListAdapter adapter = new NewsListAdapter(this, newsList, this);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.recyclerView.setAdapter(adapter);
            Toast.makeText(this, "news", Toast.LENGTH_SHORT).show();
        });

        newsListViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            Log.e(NewsListActivity.class.getSimpleName(), error);
        });
    }


    @Override
    public void onItemClick(int position) {
        if (newsList != null)
            Toast.makeText(this, newsList.get(position).getContent(), Toast.LENGTH_SHORT).show();
    }
}