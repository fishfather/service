package com.thread.join;

class RunnableImpl implements Runnable {
    public void run() {
        try {
            System.out.println("Begin sleep");
            Thread.sleep(2000);
            System.out.println("End sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadTest extends Thread {
    Thread thread;
    public ThreadTest(Thread thread) {
        this.thread = thread;
    }
    @Override
    public void run() {
        synchronized (thread) {
            System.out.println("getObjectLock");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("ReleaseObjectLock");
        }
    }
}

public class Thread_Join_Sync {
    public static void main(String[] args) {
        Thread t = new Thread(new RunnableImpl());
        new ThreadTest(t).start();
        t.start();
        try {
            t.join();
            System.out.println("joinFinish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//线程对象t的锁，并Sleep（5000）后释放，这就意味着，即使main方法t.join(1000)等待一秒钟，
//它必须等待ThreadTest 线程释放t锁后才能进入wait方法中，它实际等待时间是大概9000+1000ms
