package com.damowang.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 单例模式5
 * 枚举类型实现
 *
 */
public enum EnumSingle {
    INSTANCE;

    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}
