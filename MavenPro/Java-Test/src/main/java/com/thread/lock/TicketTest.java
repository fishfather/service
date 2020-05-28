package com.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TicketTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread t1 = new Thread(ticket);
        Thread t2 = new Thread(ticket);
        Thread t3 = new Thread(ticket);
        Thread t4 = new Thread(ticket);
        Thread t5 = new Thread(ticket);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}

class Ticket implements Runnable {
    private int tickets = 1000;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        for (; ; ) {
            lock.lock();
            try {
                if (tickets == 0)
                    break;
                try {
                    TimeUnit.MICROSECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " Sale ticket " + tickets);
                tickets--;
            } finally {
                lock.unlock();
            }
        }

    }
}