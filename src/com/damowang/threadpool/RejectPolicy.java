package com.damowang.threadpool;

/**
 * 拒绝策略
 * 可实现此接口实现不同的拒绝测率
 */
public interface RejectPolicy {
    void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);
}
