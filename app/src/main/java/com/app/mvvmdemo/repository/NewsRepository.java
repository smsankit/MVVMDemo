package com.app.mvvmdemo.repository;

import androidx.lifecycle.MutableLiveData;

import com.app.mvvmdemo.model.NewsResponse;
import com.app.mvvmdemo.network.ApiService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class NewsRepository {
    ApiService apiService;

    @Inject
    public NewsRepository(ApiService apiService){
       this.apiService =  apiService;
    }

    public Single<NewsResponse> getTopHeadLinesNews(String countryCode, String apiKey){
        return apiService.getTopHeadLinesNews(countryCode,apiKey);
    }


}
