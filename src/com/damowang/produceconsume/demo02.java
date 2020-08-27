package com.damowang.produceconsume;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 实现
 * 线程之间的通信问题：生产者和消费者问题！
 * 等待唤醒，通知唤醒！
 * 线程交替进行
 * A B 操作同一个变量 num = 0
 * A ： num + 1
 * B ： num - 1
 *
 *
 */
public class demo02 {
    public static void main(String[] args) {

        Data2 data = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}

//资源类
//判断等待、业务、通知
class Data2 {
    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        //上锁
        lock.lock();

        try {
            //TODO 判断等待
            while (number != 0) {
                condition.await();
            }
            //TODO 业务
            number ++;
            System.out.println(Thread.currentThread().getName() + "=> number = " + number);

            //TODO 通知
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        //上锁
        lock.lock();

        try {
            //TODO 判断等待
            while (number == 0) {
                condition.await();
            }
            //TODO 业务
            number --;
            System.out.println(Thread.currentThread().getName() + "=> number = " + number);

            //TODO 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }

    }

}