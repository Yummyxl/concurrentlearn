package com.wql.learn3;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/9
 *
 * 当使用 synchronized 关键字来修饰代码块时候，字节码是用 monitorenter 和 monitorexit 指令来实现锁的获取和释放动作
 *
 */

public class Test1 {

    private Object object = new Object();

    public void method() {
        synchronized (object) {
            System.out.println("hello world");
            throw new RuntimeException("e");  // 不加这句话字节码中两个 monitorexit 指令，加了之后 只剩下一个了，原因是什么呢？
            //  因为不管是 System.out.println("hello world"); 抛出异常还是 throw new RuntimeException("e"); 抛出异常，最终退出的方式只有一个就是抛出异常
            //  字节码层面最后一定是 athrow 结束，所以只有一个 monitorexit
        }
    }

    public void method2() {
        synchronized (object) {
            System.out.println("method2");
        }
    }
}
