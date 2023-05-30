package com.android.test2mvvm.test6;

import androidx.fragment.app.Fragment;

public interface MessageListener<T> {
    void getMessageFromFragment(Fragment fragment, T message);
}
