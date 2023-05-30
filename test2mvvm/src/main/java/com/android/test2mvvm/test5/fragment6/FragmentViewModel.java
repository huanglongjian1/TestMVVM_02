package com.android.test2mvvm.test5.fragment6;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FragmentViewModel extends ViewModel {
    int count;


    public FragmentViewModel(int count) {
        this.count = count;
    }

    public static class FragmentViewModelFactory implements ViewModelProvider.Factory {
        int count;

        public FragmentViewModelFactory(int count) {
            super();
            this.count = count;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new FragmentViewModel(count);
        }
    }
}
