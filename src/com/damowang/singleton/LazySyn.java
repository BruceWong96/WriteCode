package com.damowang.singleton;

/**
 * 单例模式2
 * 懒汉式线程安全（粗粒度的synchronized）
 */
public class LazySyn {
    private static LazySyn instance;

    private LazySyn() {

    }

    public static synchronized LazySyn getInstance() {
        if(instance == null) {
            instance = new LazySyn();
        }
        return instance;
    }
}
