package com.app.mvvmdemo.di.component;

import android.app.Application;

import com.app.mvvmdemo.MVVMDemoApplication;
import com.app.mvvmdemo.di.module.ActivityBindingModule;
import com.app.mvvmdemo.di.module.AppModule;
import com.app.mvvmdemo.di.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, RetrofitModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<MVVMDemoApplication> {
    void inject(MVVMDemoApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
