package com.app.mvvmdemo.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

   @Binds
    abstract Context provideContext(Application application);

}
