package com.damowang.singleton;

/**
 * 单例模式4
 * 静态内部类
 */
public class InnerClass {
    public static InnerClass getInstance() {
        return StaticInnerClass.instance;
    }

    private static class StaticInnerClass{
        private static final InnerClass instance = new InnerClass();
    }

    private InnerClass(){}


}
