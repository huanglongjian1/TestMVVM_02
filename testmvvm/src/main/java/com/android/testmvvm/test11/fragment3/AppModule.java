package com.android.testmvvm.test11.fragment3;

import com.android.testmvvm.test11.fragment2.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {
    @Singleton
    @Provides
    public User provideSomeDependency() {
        return new User("hhh", "aaaa");
    }
}
