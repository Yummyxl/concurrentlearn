package com.wql.learn1.threenumbers;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/8
 */

public class Print1 extends Thread {

    private MyCountPrint myCountPrint;

    public Print1(MyCountPrint myCountPrint) {
        this.myCountPrint = myCountPrint;
    }

    @Override
    public void run() {
        for (int i=0; i<2; i++) {
            myCountPrint.print1();
        }
    }
}
