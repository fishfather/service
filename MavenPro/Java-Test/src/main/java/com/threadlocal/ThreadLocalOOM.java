package com.threadlocal;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * vm params: -Xms50M -Xmx50M
 */

public class ThreadLocalOOM {
    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS
        ,new LinkedBlockingDeque<>());
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start.");
        for (int i=0; i< 100; i++){
            int k = i;
            poolExecutor.execute(()->{
                System.out.println("Number ["+k+"] Thread is:"+Thread.currentThread().getName());
                ThreadLocal<BigObject> thl = new ThreadLocal<>();
                thl.set(new BigObject());
            });
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("Complete.");
    }

    static class BigObject{
        // 5M
        private byte[] bytes = new byte[5 * 1024 * 1024];
    }
}
