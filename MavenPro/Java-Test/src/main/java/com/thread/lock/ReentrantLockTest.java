package com.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {

//        for(int i=0;i<5;i++){
//            new Thread(new ThreadDemo(i)).start();
//        }
        new Thread(new ThreadDemo(10)).start();

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        System.out.println("Main called");
        lock.unlock();
    }

    static class ThreadDemo implements Runnable {
        Integer id;

        public ThreadDemo(Integer id) {
            this.id = id;
        }

        public void test(){
            ReentrantLockTest.lock.lock();
            try{
                System.out.println("thread called");

                try {
                    ReentrantLockTest.lock.lock();
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                ReentrantLockTest.lock.unlock();
                ReentrantLockTest.lock.unlock();
            }

        }

        @Override
        public void run() {
            test();
//            try {
//                TimeUnit.MILLISECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            for(int i=0;i<2;i++){
//                lock.lock();
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("获得锁的线程："+id);
//                lock.unlock();
//            }
        }
    }
}
