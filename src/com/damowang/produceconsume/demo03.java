package com.damowang.produceconsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock + number 标志位 实现
 * 精准唤醒对应的线程
 * 线程之间的通信问题：生产者和消费者问题！
 * 等待唤醒，通知唤醒！
 * 线程交替进行
 * number ：1 2 3
 * 线程   ：A B C
 *
 *
 */
public class demo03 {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();
    }
}

class Data3 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int number = 1; //1A 2B 3C

    public void printA () {
        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> AAA");
            number = 2;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();

        try {
            while (number != 2) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> BBB");
            number = 3;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();

        try {
            while (number != 3) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> CCC");
            number = 1;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

