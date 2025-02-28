package com.qxy.learning.code.bio;

import java.util.concurrent.ExecutorService;

public class TimeServerHandlerExecutePool {

    private ExecutorService executor;

    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize){

    }

    public void execute(Runnable task){
        executor.execute(task);
    }
}
