package com.wql.learn2;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/9
 */

public class ThreadTest {

    public static void main(String[] args) {
        Runnable runnable = new MyThread();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}

class MyThread implements Runnable {

    int x;

    @Override
    public void run() {
        x = 0;

        while (true) {
            System.out.println(Thread.currentThread().getName() + " result " + x++);

            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (x == 30) {
                break;
            }
        }
    }
}
