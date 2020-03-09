package com.wql.learn1;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/8
 */

public class MyConunter {

    private int counter = 0;

    public synchronized void increase() {
        while (counter != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter = 1;
        System.out.println(Thread.currentThread().getName() + " 给counter + 1");
        System.out.println("counter : " + counter);
        notifyAll();
    }

    public synchronized void decrease() {
        while (counter != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter = 0;
        System.out.println(Thread.currentThread().getName() + " 给counter - 1");
        System.out.println("counter : " + counter);
        notifyAll();
    }
}