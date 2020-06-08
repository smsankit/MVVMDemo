package com.app.mvvmdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.mvvmdemo.model.NewsResponse;
import com.app.mvvmdemo.network.ApiService;
import com.app.mvvmdemo.repository.NewsRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsListViewModel extends ViewModel {
    private static final String COUNTRY_CODE = "in";
    private static final String API_KEY = "53688a9ac45b41fe86bed36b80f27843";
    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<NewsResponse> newsResponseLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable;
    private NewsRepository repository;

    @Inject
    public NewsListViewModel( NewsRepository repository) {
        this.repository = repository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public LiveData<NewsResponse> getNewsList() {
        return newsResponseLiveData;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public void getNews() {
        loading.setValue(true);
        compositeDisposable.add(repository.getTopHeadLinesNews(COUNTRY_CODE, API_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<NewsResponse>() {
                    @Override
                    public void onSuccess(NewsResponse value) {
                        loading.setValue(false);
                        newsResponseLiveData.setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.setValue(false);
                        error.setValue(e.toString());
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}
