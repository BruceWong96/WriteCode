package com.damowang.singleton;

/**
 *  单例模式1
 *  饿汉式 线程安全
 */
public class Hungry {
    private static final Hungry INSTANCE = new Hungry();
    //私有化构造函数
    private Hungry() {

    }

    //无论是否会用到，当类加载时都会生成一个对象
    //一个类的静态属性只会在第一次加载类时初始化，这是JVM保证的
    public static Hungry getInstance() {
        return INSTANCE;
    }
}
