package com.thread.join;

public class JoinTest implements Runnable{
    public static int a = 0;

    public void run() {
        for (int k = 0; k < 5; k++) {
            a = a + 1;
        }
    }

    public static void main(String[] args) throws Exception {
        Runnable r = new JoinTest();
        Thread t = new Thread(r);
        t.start();
        t.join(); //加入join()        最终a就是5
        System.out.println(a);
    }
}
