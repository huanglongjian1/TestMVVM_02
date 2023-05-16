package com.android.test2mvvm.test1.fragment8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorUtil {
    public static void execute(Runnable runnable) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(runnable);
        service.shutdown();
    }
}
