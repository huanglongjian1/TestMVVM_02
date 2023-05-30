package com.android.test2mvvm.test5.fragment6.aac;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;

import com.android.test2mvvm.test5.fragment4.bean.Person;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AAC_ViewModel extends AndroidViewModel {
    private MutableLiveData<Person> mutableLiveData = new MutableLiveData<>();
    private LiveData<String> stringMutableLiveData;
    private LiveData<Integer> integerLiveData;
    private LiveData<String> stringLiveData;


    public AAC_ViewModel(@NonNull Application application) {
        super(application);
        Observable.interval(2, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        Person person = new Person("AAAA" + aLong, aLong.toString());
                        mutableLiveData.postValue(person);
                    }
                });
        stringMutableLiveData = Transformations.map(mutableLiveData, new Function<Person, String>() {
            @Override
            public String apply(Person input) {

                return input.getName() + ":" + input.getPsw();
            }
        });
        integerLiveData=Transformations.switchMap(mutableLiveData, new Function<Person, LiveData<Integer>>() {
            @Override
            public LiveData<Integer> apply(Person input) {
                MutableLiveData<Integer> integerMutableLiveData=new MutableLiveData<>();
                integerMutableLiveData.setValue(input.getName().length());
                return integerMutableLiveData;
            }
        });



    }

    public LiveData<String> getStringMutableLiveData() {
        return stringMutableLiveData;
    }

    public LiveData<Integer> getIntegerLiveData() {
        return integerLiveData;
    }

    public MutableLiveData<Person> getMutableLiveData() {
        return mutableLiveData;
    }
}
