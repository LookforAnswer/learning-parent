package com.qxy.learning.code;

import java.lang.reflect.InvocationTargetException;

public class Test {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /*List<Integer> integerList = new ArrayList<>();
        integerList.getClass().getMethod("add", Object.class).invoke(integerList,"abc");
        for (int i = 0 ; i < integerList.size(); i++ ){
            System.out.println(integerList.get(i));
        }*/

        String a = "aaa";
        String b = "aaa";

        System.out.println(a.equals(b));

        String aa = new String("ccc");
        String bb = new String("ccc");
        System.out.println(aa.equals(bb));

    }
}
