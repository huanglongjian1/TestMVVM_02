package com.android.testmvvm.test9.hilt;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

//详见上面的 hilt 对应组件
@InstallIn(ApplicationComponent.class)
@Module
public class HttpModule {

    @Singleton
    @Provides
    public HttpObject getHttpObject(){
        return new HttpObject();
    }
}

