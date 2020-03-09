package com.wql.learn1;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/8
 */

public class IncreaseThread extends Thread {

    private MyConunter myConunter;

    public IncreaseThread(MyConunter myConunter) {
        this.myConunter = myConunter;
    }

    @Override
    public void run() {
        for (int i=0; i<30; i++) {
            myConunter.increase();
            try {
                Thread.sleep((long)(1000*Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
