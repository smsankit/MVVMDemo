package com.app.mvvmdemo.di.module;

import com.app.mvvmdemo.view.NewsListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract NewsListActivity bindNewsListActivity();
}
