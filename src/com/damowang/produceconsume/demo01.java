package com.damowang.produceconsume;

/**
 * synchronized 实现
 * 线程之间的通信问题：生产者和消费者问题！
 * 等待唤醒，通知唤醒！
 * 线程交替进行
 * A B 操作同一个变量 num = 0
 * A ： num + 1
 * B ： num - 1
 *
 *
 */
public class demo01 {
    public static void main(String[] args) {

        Data data = new Data();
        new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for(int i = 0; i < 10; i++) {
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
class Data {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //TODO 判断等待
        while (number != 0) {
            this.wait();
        }
        //TODO 业务
        number ++;
        System.out.println(Thread.currentThread().getName() + "=> number = " + number);

        //TODO 通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //TODO 判断等待
        while (number == 0) {
            this.wait();
        }
        //TODO 业务
        number --;
        System.out.println(Thread.currentThread().getName() + "=> number = " + number);

        //TODO 通知
        this.notifyAll();
    }

}