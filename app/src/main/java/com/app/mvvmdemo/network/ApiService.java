package com.app.mvvmdemo.network;

import com.app.mvvmdemo.model.NewsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("top-headlines")
    Single<NewsResponse> getTopHeadLinesNews(@Query("country") String country,
                                             @Query("apiKey") String apiKey);
}
