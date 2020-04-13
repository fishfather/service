package com.thread.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownTest {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 运行");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            }).start();
        }

        System.out.println("等待子线程运行结束");
//        latch.await(10, TimeUnit.SECONDS);
        latch.await();
        System.out.println("子线程运行结束");
    }
}
