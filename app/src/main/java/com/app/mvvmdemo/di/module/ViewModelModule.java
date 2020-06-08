package com.app.mvvmdemo.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.app.mvvmdemo.repository.NewsRepository;
import com.app.mvvmdemo.util.ViewModelFactory;
import com.app.mvvmdemo.di.ViewModelKey;
import com.app.mvvmdemo.viewmodel.NewsListViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel.class)
    abstract ViewModel bindListViewModel(NewsListViewModel listViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
