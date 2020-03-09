package com.wql.learn1;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/8
 *
 * 0 1 0 1 0 1轮流打印
 */

public class Client {

    public static void main(String[] args) {
        MyConunter myConunter = new MyConunter();
        DecreaseThread decreaseThread = new DecreaseThread(myConunter);
        IncreaseThread increaseThread = new IncreaseThread(myConunter);
        decreaseThread.start();
        increaseThread.start();
    }
}