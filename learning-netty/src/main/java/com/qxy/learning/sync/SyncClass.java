package com.qxy.learning.sync;

public class SyncClass {
    public synchronized void testMethod(){
        System.out.println("test method");
    }

    public void testBlock(){
        synchronized (this){
            System.out.println("test block");
        }
    }
}
