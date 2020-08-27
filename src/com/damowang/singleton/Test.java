package com.damowang.singleton;

public class Test {
    public static void main(String[] args) {
        InnerClass class1 = InnerClass.getInstance();
        InnerClass class2 = InnerClass.getInstance();
        System.out.println(class1 == class2);

        EnumSingle.INSTANCE.getId();
    }
}
