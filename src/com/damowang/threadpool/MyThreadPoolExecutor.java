package com.damowang.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO 手写实现一个线程池
 * @author weiyouchen
 * 2020-08-26
 */
public class MyThreadPoolExecutor implements Executor {
    //线程池名称
    private String name;

    //核心线程数
    private int coreSize;

    //最大线程数
    private int maxSize;

    //任务队列
    private BlockingQueue<Runnable> taskQueue;

    //拒绝策略
    private RejectPolicy rejectPolicy;

    //线程序列号
    private AtomicInteger sequence = new AtomicInteger(0);

    //当前正在运行的线程数
    //需要修改时，线程间立刻感知，所以用AtomicInteger
    //也可以用volatile 并结合 Unsafe 做CAS 操作
    private AtomicInteger runningCount = new AtomicInteger(0);

    /**
     * 构造函数初始化
     * @param name
     * @param coreSize
     * @param maxSize
     * @param taskQueue
     * @param rejectPolicy
     */
    public MyThreadPoolExecutor(String name, int coreSize, int maxSize, BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy) {
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = taskQueue;
        this.rejectPolicy = rejectPolicy;
    }

    /**
     * 首先，如果运行的线程数小于核心线程数，直接创建一个新的核心线程来运行新的任务。
     * 其次，如果运行的线程数达到了核心线程数，则把新任务入队列。
     * 然后，如果队列也满了，则创建新的非核心线程来运行新的任务。
     * 最后，如果非核心线程数也达到最大了，那就执行拒绝策略。
     * @param task
     */
    @Override
    public void execute(Runnable task) {
        //正在运行的线程数
        int count = runningCount.get();

        //如果正在运行的线程数小于核心线程数，直接加一个线程
        if (count < coreSize) {
            //注意：这里不一定判断成功，addWork()方法里还要判断一次是不是真的小于
            if(addWorker(task, true)) {
                return;
            }
            //如果添加核心线程数失败，进入下面的逻辑
        }

        // 如果达到了核心线程数，先尝试让任务入队
        // 这里之所以用offer(), 是因为如果队列满了 offer 会立即返回false
        if (taskQueue.offer(task)) {
            //do nothing, 为了逻辑清晰
        } else {
            //如果入队失败，说明队列满了，那就添加一个非核心线程
            if (!addWorker(task, false)) {
                rejectPolicy.reject(task, this);
            }
        }
    }

    private boolean addWorker(Runnable newTask, boolean isCore) {
        //自旋判断是不是真的可以创建一个线程
        for (;;) {
            //正在运行的线程数
            int count = runningCount.get();
            //核心线程还是非核心线程
            int max = isCore ? coreSize : maxSize;
            //不满足创建线程的条件，则直接返回false
            if (count >= max) {
                return false;
            }

            //修改 runningCount 成功，可以创建线程
            if (runningCount.compareAndSet(count, count + 1)) {
                //线程的名字
                String threadName = (isCore ? "core_" : "") + name + sequence.incrementAndGet();
                //创建线程并启动
                new Thread(() -> {
                    System.out.println("thread name : " + Thread.currentThread().getName());
                    //运行的任务
                    Runnable task = newTask;
                    //不断的从任务队列中取任务运行，如果取出来的任务为null，则跳出循环，线程也就结束了
                    while (task != null || (task = getTask()) != null) {
                        try {
                            task.run();
                        } finally {
                            task = null;
                        }
                    }
                }, threadName).start();
                break;
            }
        }
        return true;
    }

    private Runnable getTask() {
        try {
            //take() 方法会一直阻塞到取到任务为止
            return taskQueue.take();
        } catch (InterruptedException e) {
            runningCount.decrementAndGet();
            return null;
        }
    }

}
