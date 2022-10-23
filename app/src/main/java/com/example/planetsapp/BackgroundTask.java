package com.example.planetsapp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BackgroundTask {
    private static Executor executor = Executors.newSingleThreadExecutor();

    public static void execute(Runnable runnable){
        executor.execute(runnable);
    }
}
