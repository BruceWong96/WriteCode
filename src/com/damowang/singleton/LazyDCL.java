package com.damowang.singleton;

/**
 * 单例模式3：
 * 懒汉式线程安全
 * 双重校验锁
 */
public class LazyDCL {
    private static volatile LazyDCL instance;

    private LazyDCL() {

    }

    public static LazyDCL getInstance() {
        if(instance == null) {
            synchronized (LazyDCL.class) {
                if(instance == null) {
                    instance = new LazyDCL();
                }
            }
        }
        return instance;
    }
}
