package com.wql.learn3;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/10
 *
 * synchronized 修饰静态方法时候，即方法标志有 ACC_STATIC, ACC_SYNCHRONIZED，这是一个静态同步方法，执行线程就会尝试获取 这个方法所在类class
 * 对应的monitor锁
 */

public class Test3 {

    // flags ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
    public static synchronized void method() {
        System.out.println("method");
    }
}
