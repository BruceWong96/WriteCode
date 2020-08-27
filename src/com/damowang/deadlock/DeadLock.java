package com.damowang.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁代码模拟
 */
public class DeadLock{
    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        Object object1 = new Object();
        Object object2 = new Object();

        new Thread(() -> {
            synchronized(object1) {
                System.out.println(Thread.currentThread().getName() + "获取了object1");
                try{
//                    Thread.sleep(3000);
                    TimeUnit.SECONDS.sleep(3);
                } catch(Exception e){

                    e.printStackTrace();
                }
                synchronized(object2) {
                    System.out.println(Thread.currentThread().getName() + "获取了object2");
                }
            }
        }, "AAA").start();

        new Thread(() -> {
            synchronized(object2) {
                System.out.println(Thread.currentThread().getName() + "获取了object2");
                try{
                    Thread.sleep(3000);
                } catch(Exception e){
                    e.printStackTrace();
                }
                synchronized(object1) {
                    System.out.println(Thread.currentThread().getName() + "获取了object1");
                }
            }
        }, "BBB").start();
    }
}
