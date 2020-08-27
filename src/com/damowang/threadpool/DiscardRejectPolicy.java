package com.damowang.threadpool;

/**
 * TODO 丢弃拒绝策略
 */
public class DiscardRejectPolicy implements RejectPolicy{
    @Override
    public void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor) {
        //do nothing
        System.out.println("discard one task");
    }
}
