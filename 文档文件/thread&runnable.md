## Thread

```
/**
 * A <i>thread</i> is a thread of execution in a program. The Java       一个线程指的是程序当中的一个执行线程。
 * Virtual Machine allows an application to have multiple threads of    java虚拟机允许一个应用拥有多个线程并且可以并发的运行。
 * execution running concurrently.
 * <p>
 * Every thread has a priority. Threads with higher priority are         每个线程有一个优先级。更高优先级的线程执行的情况优先于更低优先级的线程。
 * executed in preference to threads with lower priority. Each thread
 * may or may not also be marked as a daemon. When code running in       每个线程可以或者也可以不标记为后台线程。当运行在某一个线程中的代码创建来一个新的线程对象的时候，
 * some thread creates a new <code>Thread</code> object, the new
 * thread has its priority initially set equal to the priority of the    新的线程有一个同等于创建他的线程的初始化优先级，
 * creating thread, and is a daemon thread if and only if the            在并且只在创建线程为后台线程的情况下新创建的线程为后台线程。
 * creating thread is a daemon.
 * <p>
 * When a Java Virtual Machine starts up, there is usually a single      当一个java虚拟机启动，通常会有一个单个的非daemon的线程，
 * non-daemon thread (which typically calls the method named             这个非daemon线程通常会调用所指定类的main方法。
 * <code>main</code> of some designated class). The Java Virtual         java虚拟机会继续执行线程直到下边两种情况发生
 * Machine continues to execute threads until either of the following
 * occurs:
 * <ul>
 * <li>The <code>exit</code> method of class <code>Runtime</code> has been   class Runtime的exit方法被调用并且安全管理器允许退出操作发生。
 *     called and the security manager has permitted the exit operation
 *     to take place.
 * <li>All threads that are not daemon threads have died, either by         所有的非后台线程都已经消亡，
 *     returning from the call to the <code>run</code> method or by         要么run方法返回，要么抛出异常并传播到run方法之外。
 *     throwing an exception that propagates beyond the <code>run</code>
 *     method.
 * </ul>
 * <p>
 * There are two ways to create a new thread of execution. One is to     有两种方法可以创建一个新的执行线程。
 * declare a class to be a subclass of <code>Thread</code>. This       一种声明一个类成为Thread的子类。
 * subclass should override the <code>run</code> method of class        这个子类应该重写Thread的run方法。
 * <code>Thread</code>. An instance of the subclass can then be        这个子类可以被分配并且启动。
 * allocated and started. For example, a thread that computes primes    比如说一个线程计算大于某个状态值可以这样写：
 * larger than a stated value could be written as follows:
 * <hr><blockquote><pre>
 *     class PrimeThread extends Thread {
 *         long minPrime;
 *         PrimeThread(long minPrime) {
 *             this.minPrime = minPrime;
 *         }
 *
 *         public void run() {
 *             // compute primes larger than minPrime
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </pre></blockquote><hr>
 * <p>
 * The following code would then create a thread and start it running:
 * <blockquote><pre>
 *     PrimeThread p = new PrimeThread(143);
 *     p.start();
 * </pre></blockquote>
 * <p>
 * The other way to create a thread is to declare a class that      创建线程的另一种方法是声明一个类实现 Runnable接口。
 * implements the <code>Runnable</code> interface. That class then   这个类然后实现run方法。
 * implements the <code>run</code> method. An instance of the class can   这个类的实例可以被分配，
 * then be allocated, passed as an argument when creating   当创建一个Thread的时候当作参数传递，
 * <code>Thread</code>, and started. The same example in this other   然后启动。例子如下
 * style looks like the following:
 * <hr><blockquote><pre>
 *     class PrimeRun implements Runnable {
 *         long minPrime;
 *         PrimeRun(long minPrime) {
 *             this.minPrime = minPrime;
 *         }
 *
 *         public void run() {
 *             // compute primes larger than minPrime
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </pre></blockquote><hr>
 * <p>
 * The following code would then create a thread and start it running:        
 * <blockquote><pre>
 *     PrimeRun p = new PrimeRun(143);
 *     new Thread(p).start();
 * </pre></blockquote>
 * <p>
 * Every thread has a name for identification purposes. More than         每个类有个名字用来辨别。
 * one thread may have the same name. If a name is not specified when     多个线程可以公用相同的名字。如果线程创建时候没有指定名字，
 * a thread is created, a new name is generated for it.                  会生成一个新名字给这个线程。
 * <p>
 * Unless otherwise noted, passing a {@code null} argument to a constructor     除非特别说明，传递一个null参数给构造参数或者方法在这个类里将会造成 NullPointerException
 * or method in this class will cause a {@link NullPointerException} to be
 * thrown.
 *
 * @author  unascribed
 * @see     Runnable
 * @see     Runtime#exit(int)
 * @see     #run()
 * @see     #stop()
 * @since   JDK1.0
 */
public
class Thread implements Runnable

```

## Runnable 

```
/**
 * The <code>Runnable</code> interface should be implemented by any      Runnable接口应该被任何一个类实现，这个类的实例将要被一个线程去执行。
 * class whose instances are intended to be executed by a thread. The
 * class must define a method of no arguments called <code>run</code>.   这个类必须定义一个无参的run方法。
 * <p>
 * This interface is designed to provide a common protocol for objects that    这个接口被设计去提供一个公共的协议针对想要在激活状态下执行代码的对象。
 * wish to execute code while they are active. For example,
 * <code>Runnable</code> is implemented by class <code>Thread</code>.        比如 Runnable 被class Thread实现。
 * Being active simply means that a thread has been started and has not      激活意味着 一个线程已经启动且还没有停止。
 * yet been stopped.
 * <p>
 * In addition, <code>Runnable</code> provides the means for a class to be   另外，Runnable为class提供了一些方式当激活状态并且不是Thread的子类。
 * active while not subclassing <code>Thread</code>. A class that implements
 * <code>Runnable</code> can run without subclassing <code>Thread</code>   一个类实现类Runnable可以运行无需子类化Thread通过实例话一个Thread实例然后将自己作为目标传递。
 * by instantiating a <code>Thread</code> instance and passing itself in
 * as the target.  In most cases, the <code>Runnable</code> interface should    多数情况下，RUnnable 接口应该被用于 你只打算重写run方法而不重写Thrad的其他方法。
 * be used if you are only planning to override the <code>run()</code>
 * method and no other <code>Thread</code> methods.
 * This is important because classes should not be subclassed     这很重要，因为class不应该成为子类除非程序开发者倾向于修改或者增强class的基础行为。
 * unless the programmer intends on modifying or enhancing the fundamental
 * behavior of the class.
 *
 * @author  Arthur van Hoff
 * @see     java.lang.Thread
 * @see     java.util.concurrent.Callable
 * @since   JDK1.0
 */
@FunctionalInterface
public interface Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used  当实现了接口Runnable的对象去创建一个线程，启动一个线程会导致在另外一个独立的线程run方法会被调用。
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may   这个方法通用的契约是 任何都可能被执行。
     * take any action whatsoever.
     *
     * @see     java.lang.Thread#run()
     */
    public abstract void run();
}
```