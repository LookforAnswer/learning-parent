package com.qxy.learning.code.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    public static Unsafe unsafe;

    static {
        try {
            Field theSafe = Unsafe.class.getDeclaredField("theUnsafe");
            theSafe.setAccessible(true);
            unsafe = (Unsafe) theSafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(unsafe.addressSize());
        System.out.println(unsafe.pageSize());
    }
}
