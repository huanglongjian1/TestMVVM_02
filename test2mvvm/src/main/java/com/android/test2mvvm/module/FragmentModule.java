package com.android.test2mvvm.module;

import androidx.fragment.app.Fragment;

import com.android.test2mvvm.test1.fragment.Test1_Fragment;
import com.android.test2mvvm.test1.fragment2.Test2_fragment;
import com.android.test2mvvm.test1.fragment4.Test1_fragment4;
import com.android.test2mvvm.test1.fragment8.bean.User;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public final class FragmentModule {

    @Provides
    public User provides_User() {
        User user = new User();
        user.setName("hlj");
        return user;
    }

    @Provides
    @test1
    public Test2_fragment provides_Fragment2() {
        return new Test2_fragment();
    }

    @Provides
    @test2
    public Test2_fragment provides_Fragment3() {
        return new Test2_fragment();
    }


    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface test1 {

    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface test2 {

    }

    @Provides
    public Test1_fragment4 provides_Fragment4() {
        return new Test1_fragment4();
    }
}