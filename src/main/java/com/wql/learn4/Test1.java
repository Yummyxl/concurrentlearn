package com.wql.learn4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/15
 */

public class Test1 {

    private Lock lock = new ReentrantLock();

    public void method1() {

        try {
            lock.lock();
            System.out.println("method 1");
        } finally {
            lock.unlock();
        }
    }

    public void method2() {

        try {
            lock.lock();
            System.out.println("method 2");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();

        Thread thread1 = new Thread(() -> {
            for (int i=0; i<100; i++) {
                test1.method1();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i=0; i<100; i++) {
                test1.method2();
            }
        });

        thread1.start();
        thread2.start();

    }
}
