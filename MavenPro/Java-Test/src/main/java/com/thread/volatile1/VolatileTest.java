package com.thread.volatile1;

import java.util.concurrent.TimeUnit;

/**
 * java内存分为工作内存和主存
 *
 * 1. 工作内存：即java线程的本地内存，是单独给某个线程分配的，存储局部变量等，同时也会复制主存的共享变量作为本地的副本，目的是为了减少和主存通信的频率，提高效率。
 *
 * 主存：存储类成员变量等
 *
 * 2. 可见性是指的是线程访问变量是否是最新值。
 *
 * 局部变量不存在可见性问题，而共享内存就会有可见性问题，因为本地线程在创建的时候，会从主存中读取一个共享变量的副本，
 * 且修改也是修改副本，且并不是立即刷新到主存中去，那么其他线程并不会马上共享变量的修改。
 *
 * 因此，主线程修改共享变量flag后，线程T1并不会马上知晓，就会出现上述死循环的问题。
 *
 * 3. 重排序：重排序是指为了提高指令运行的性能，在编译时或者运行时对指令执行顺序进行调整的机制。重排序分为编译重排序和运行时重排序。编译重排序是指编译器在编译源代码的时候就对代码执行顺序进行分析，
 * 在遵循as-if-serial的原则前提下对源码的执行顺序进行调整。as-if-serial原则是指在单线程环境下，无论怎么重排序，代码的执行结果都是确定的。运行时重排序是指为了提高执行的运行速度，系统对机器的执行指令的执行顺序进行调整。
 */

public class VolatileTest {
    private static boolean flag = false;
//    private volatile static boolean flag = false;
    private static int i=0;

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println("T1 Started.");
            while(flag == false){
                i++;
            }

            System.out.println("T1 Stopped.");
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main start "+i);
        flag = true;
    }
}

