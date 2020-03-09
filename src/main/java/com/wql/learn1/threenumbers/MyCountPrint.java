package com.wql.learn1.threenumbers;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/8
 */

public class MyCountPrint {

    private int count = 1;

    public synchronized void print1() {
        while (count != 1) {
            try {
                System.out.println(System.currentTimeMillis()+ "print1 detail");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + " ==  " + count);
        count = 2;
        notifyAll();
    }

    public synchronized void print2() {
        while (count != 2) {
            try {
                System.out.println(System.currentTimeMillis() + "print2 detail");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + " ==  " + count);
        count = 3;
        notifyAll();
    }

    public synchronized void print3() {
        while (count != 3) {
            try {
                System.out.println(System.currentTimeMillis() + "print3 detail");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + " ==  " + count);
        count = 1;
        notifyAll();
    }
}
