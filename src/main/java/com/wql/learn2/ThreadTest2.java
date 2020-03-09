package com.wql.learn2;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/9
 *
 * 如果实例方法上加了 synchronized 方法，那么锁的是this这个实例对象
 *
 * 如果 static 方法 加了 synchronized 其实是在这个类的class对象傻姑娘加锁
 *
 */

public class ThreadTest2 {

    public static void main(String[] args) {
        // hello world
       /* MyClass myClass = new MyClass();

        Thread1 thread1 = new Thread1(myClass);
        Thread2 thread2 = new Thread2(myClass);

        thread1.start();

        try {
            Thread.sleep(900L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        thread2.start();*/

       // world hello
        MyClass myClass = new MyClass();
        MyClass myClass2 = new MyClass();

        Thread1 thread1 = new Thread1(myClass);
        Thread2 thread2 = new Thread2(myClass2);

        thread1.start();

        try {
            Thread.sleep(900L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        thread2.start();
    }
}

class MyClass {

    public synchronized void hello() {
        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello ");
    }

    public synchronized void world() {
        System.out.println("world ");
    }
}

class Thread1 extends Thread {

    private MyClass myClass;

    public Thread1(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.hello();
    }
}

class Thread2 extends Thread {

    private MyClass myClass;

    public Thread2(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.world();
    }
}
