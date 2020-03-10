package com.wql.learn3;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Author: Yummyxl
 * @Date: 2020/3/10
 *
 * 对于 synchronized 关键字来说，修饰方法并没有出现 monitorenter 和 monitorexit指令，而是出现了一个 ACC_SYNCHRONIZED flag标志,
 * 原因是 jvm 使用了 ACC_SYNCHRONIZED 标志来区分一个方法是否问同步方法，当方法被调用时候，调用指令会检查该方法是否拥有 ACC_SYNCHRONIZED
 * 标志，如果有，那么执行线程将会先持有方法所在对象的monitor锁，然后再去执行方法体，其他线程均无法获取对象的monitor锁，当线程执行完毕后，依然会释放掉
 * monitor对象，即便方法抛出异常，也会释放掉
 */

public class Test2 {

    //字节码中  flags: ACC_PUBLIC, ACC_SYNCHRONIZED
    public synchronized void method() {
        System.out.println("method");
    }
}
