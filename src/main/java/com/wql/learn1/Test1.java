package com.wql.learn1;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/6
 *
 */

public class Test1 {

    public static void main(String[] args) throws InterruptedException {

        Object object = new Object();

        synchronized (object) {
            object.wait();
        }
    }
}
