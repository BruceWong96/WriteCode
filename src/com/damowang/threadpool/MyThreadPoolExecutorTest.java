package com.damowang.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试线程池
 */
public class MyThreadPoolExecutorTest {
    public static void main(String[] args) {
        Executor threadPool = new MyThreadPoolExecutor("大魔王出品线程池", 5, 10, new ArrayBlockingQueue<Runnable>(15), new DiscardRejectPolicy());
        AtomicInteger num = new AtomicInteger(0);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Running: " + System.currentTimeMillis() + ": " + num.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
