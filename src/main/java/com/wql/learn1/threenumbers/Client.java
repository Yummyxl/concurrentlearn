package com.wql.learn1.threenumbers;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/8
 *
 * 按照 1 2 3 1 2 3 的顺序来打印
 */

public class Client {

    public static void main(String[] args) {
        MyCountPrint myCountPrint = new MyCountPrint();

        Print1 print1 = new Print1(myCountPrint);
        Print2 print2 = new Print2(myCountPrint);
        Print3 print3 = new Print3(myCountPrint);

        print1.start();
        print2.start();
        print3.start();
    }
}
