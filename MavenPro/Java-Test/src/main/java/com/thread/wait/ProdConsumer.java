package com.thread.wait;

import java.util.concurrent.TimeUnit;

public class ProdConsumer {
    public static void main(String[] args) {
        Container c = new Container(1000000);
        Thread pro = new Thread(new Produce(c));
        Thread con = new Thread(new Consumer(c));
        Thread con2 = new Thread(new Consumer(c));
        pro.start();
        con.start();
        con2.start();
    }
}

class Produce implements Runnable{
    private Container c;
    public Produce(Container c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            c.produce();
        }
    }
}

class Consumer implements Runnable{
    private Container c;
    public Consumer(Container c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            c.eat();
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

class Container{
    int size;
    String[] list = null;
    int hamCount = 0;

    public Container(int size) {
        this.size = size;
        list = new String[size];
    }

    protected synchronized void produce(){
        if(hamCount >= list.length){
            //full - wait to consume
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            list[hamCount] = "Hamburger "+hamCount;
            System.out.println(Thread.currentThread().getName()+" Produce Hamburger in position "+ hamCount);
            hamCount++;
            this.notifyAll();
        }
    }

    protected synchronized void eat(){
        if(hamCount <=0){
            //wait to produce
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(Thread.currentThread().getName()+ " Eat Hamburger "+list[hamCount-1]);
            hamCount--;
            this.notifyAll();
        }
    }
}
