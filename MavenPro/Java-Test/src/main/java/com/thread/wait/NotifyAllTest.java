package com.thread.wait;

public class NotifyAllTest {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Container c = new Container(2);

        new Thread(()->{
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() + ": start.");
                try {
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + ": get notified with len:"+c.len);
                    if(c.len>=0){
                        c.len--;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": end.");
            }
        }).start();

        new Thread(()->{
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() + ": start.");
                try {
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + ": get notified with len:"+c.len);
                    if(c.len>=0){
                        c.len--;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": end.");
            }
        }).start();

        new Thread(()->{
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() + ": start.");

//                obj.notify();
                c.len --;
                obj.notifyAll();
                System.out.println(Thread.currentThread().getName() + ": end.");
            }
        }).start();

        Thread.sleep(10000);
        System.out.println("Complete");
    }

    static class Container{
        int len = 0;
        Container(int len){
            this.len = len;
        }
    }
}
