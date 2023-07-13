package com.android.test2mvvm.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorUtil {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    public static <T> T submit(Callable<T> callable) {
        Future<T> future = executor.submit(callable);
        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return null;
    }
}
