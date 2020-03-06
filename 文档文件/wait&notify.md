## wait

```
/**
     * Causes the current thread to wait until either another thread invokes the
     * {@link java.lang.Object#notify()} method or the
     * {@link java.lang.Object#notifyAll()} method for this object, or a
     * specified amount of time has elapsed.
     * <p>
     * The current thread must own this object's monitor.         当前线程必须拥有对象的 monitor。
     * <p>
     * This method causes the current thread (call it <var>T</var>) to   这个方法导致当前线程（成为T）把自己移动到这个对象的等待队列并且
     * place itself in the wait set for this object and then to relinquish   然后去放弃任何在这个对象上的同步要求。
     * any and all synchronization claims on this object. Thread <var>T</var>
     * becomes disabled for thread scheduling purposes and lies dormant   线程T由于线程调用而无法使用并且躺在等待对立里休眠直到下面四种发生：
     * until one of four things happens:
     * <ul>
     * <li>Some other thread invokes the {@code notify} method for this      其他线程待用来对象的notify方法然后线程T有概率成为任意被唤醒的线程。
     * object and thread <var>T</var> happens to be arbitrarily chosen as
     * the thread to be awakened.
     * <li>Some other thread invokes the {@code notifyAll} method for this   其他线程调用了对象的 notifyAll方法。
     * object.
     * <li>Some other thread {@linkplain Thread#interrupt() interrupts}   其他线程调用了T的 Thread#interrupt() 方法。
     * thread <var>T</var>.
     * <li>The specified amount of real time has elapsed, more or less.  If  指定的时间过去了，可能的可能少。
     * {@code timeout} is zero, however, then real time is not taken into   如果指定时间为0，那么这个时间不会被考虑，线程简单的一直等着被通知。
     * consideration and the thread simply waits until notified.
     * </ul>
     * The thread <var>T</var> is then removed from the wait set for this     然后线程T被从对象的等待队列中一处然后去重新启用线程调度。
     * object and re-enabled for thread scheduling. It then competes in the
     * usual manner with other threads for the right to synchronize on the   然后线程T和其他的线程以通常的方式在对象上竞争同步的权利，
     * object; once it has gained control of the object, all its         一旦T获取了控制对象的权利，
     * synchronization claims on the object are restored to the status quo   所有他在这个对象的同步请求恢复到 wait 方法被调用时候到样子。
     * ante - that is, to the situation as of the time that the {@code wait}    
     * method was invoked. Thread <var>T</var> then returns from the    线程T然后从 wait 方法的调用返回。
     * invocation of the {@code wait} method. Thus, on return from the    这样，从 wait 方法中返回，
     * {@code wait} method, the synchronization state of the object and of   对象和线程T的同步状态正是 wait 方法被调用的时候。
     * thread {@code T} is exactly as it was when the {@code wait} method
     * was invoked.
     * <p>
     * A thread can also wake up without being notified, interrupted, or   一个线程也可以在 被通知、被中断或者超时 这三个操作之外 醒来，
     * timing out, a so-called <i>spurious wakeup</i>.  While this will rarely   被称为 虚假唤醒。
     * occur in practice, applications must guard against it by testing for     这个在实践中很少出现，应用必须通过测试是否满足线程唤醒的条件来守护这种情况，
     * the condition that should have caused the thread to be awakened, and    并且在条件不满足时候继续 wait，换句话说，wait 应该总是在一个循环中。
     * continuing to wait if the condition is not satisfied.  In other words,
     * waits should always occur in loops, like this one:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait(timeout);
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * (For more information on this topic, see Section 3.2.3 in Doug Lea's
     * "Concurrent Programming in Java (Second Edition)" (Addison-Wesley,
     * 2000), or Item 50 in Joshua Bloch's "Effective Java Programming
     * Language Guide" (Addison-Wesley, 2001).
     *
     * <p>If the current thread is {@linkplain java.lang.Thread#interrupt() 如果当前线程在等待之前或者正在等待的时候被其他线程打断，
     * interrupted} by any thread before or while it is waiting, then an    会抛出一个 InterruptedException 异常。
     * {@code InterruptedException} is thrown.  This exception is not
     * thrown until the lock status of this object has been restored as     在还原此对象的锁定状态（如上所述）之前，不会引发此异常。
     * described above.                                                     意思是抛出异常时候锁定状态会被重置为原来的样子。
     *
     * <p>
     * Note that the {@code wait} method, as it places the current thread   对于wait方法，当前线程会把自己移动到对象的等待队列，
     * into the wait set for this object, unlocks only this object; any     仅仅不再锁定这个对象，
     * other objects on which the current thread may be synchronized remain 这个线程同步的任何其他的对象仍然保持锁定状态。
     * locked while the thread waits.
     * <p>
     * This method should only be called by a thread that is the owner      这个方法应该在线程持有对象的 monitor 前提下调用。
     * of this object's monitor. See the {@code notify} method for a        看notify 方法获取一个线程如何成为对象 monitor 的方式的描述。
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @param      timeout   the maximum time to wait in milliseconds.
     * @throws  IllegalArgumentException      if the value of timeout is
     *               negative.
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of the object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#notifyAll()
     */
    public final native void wait(long timeout) throws InterruptedException;

    /**
     * Causes the current thread to wait until another thread invokes the   导致当前线程等待直达其他线程调用了这个对象的 notify 或 notifyAll 方法，
     * {@link java.lang.Object#notify()} method or the
     * {@link java.lang.Object#notifyAll()} method for this object, or  或者其他的线程中断了当前线程，
     * some other thread interrupts the current thread, or a certain  或者获取了一个确定的时间。
     * amount of real time has elapsed.
     * <p>
     * This method is similar to the {@code wait} method of one          这个方法和一个参数的 wait 方法很类似，
     * argument, but it allows finer control over the amount of time to      但是这个方法允许高质量的控制在放弃等待通知之前的时间。
     * wait for a notification before giving up. The amount of real time,   这个真正的时间用 纳秒 来衡量
     * measured in nanoseconds, is given by:
     * <blockquote>
     * <pre>
     * 1000000*timeout+nanos</pre></blockquote>            1000000 * timeout + nanos
     * <p>
     * In all other respects, this method does the same thing as the   在所有其他方面，这个方法和单参数的方法一样。
     * method {@link #wait(long)} of one argument. In particular,  
     * {@code wait(0, 0)} means the same thing as {@code wait(0)}.  尤其是  wait(0, 0) 和  wait(0) 一样。
     * <p>
     * The current thread must own this object's monitor. The thread
     * releases ownership of this monitor and waits until either of the
     * following two conditions has occurred:
     * <ul>
     * <li>Another thread notifies threads waiting on this object's monitor    其他线程通过调用对象的 monitor 方法来通知在这个对象的 monitor 上正在等待的线程醒来。
     *     to wake up either through a call to the {@code notify} method
     *     or the {@code notifyAll} method.
     * <li>The timeout period, specified by {@code timeout}                   超时
     *     milliseconds plus {@code nanos} nanoseconds arguments, has
     *     elapsed.
     * </ul>
     * <p>
     * The thread then waits until it can re-obtain ownership of the
     * monitor and resumes execution.
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are
     * possible, and this method should always be used in a loop:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait(timeout, nanos);
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @param      timeout   the maximum time to wait in milliseconds.
     * @param      nanos      additional time, in nanoseconds range
     *                       0-999999.
     * @throws  IllegalArgumentException      if the value of timeout is
     *                      negative or the value of nanos is
     *                      not in the range 0-999999.
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     */
    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }

        if (nanos > 0) {
            timeout++;
        }

        wait(timeout);
    }

    /**
     * Causes the current thread to wait until another thread invokes the       导致当前线程等待直到另外一个线程调用了当前对象的 notify 或者 notifyAll方法。
     * {@link java.lang.Object#notify()} method or the       
     * {@link java.lang.Object#notifyAll()} method for this object.             
     * In other words, this method behaves exactly as if it simply              换言之，这个方法和 wait(0) 是一样的。
     * performs the call {@code wait(0)}.
     * <p>
     * The current thread must own this object's monitor. The thread           当前线程必须拥有这个对象的 monitor（监视器）。
     * releases ownership of this monitor and waits until another thread        这个线程会释放 monitor 的拥有权直到其他线程通知在这个 monitor 上等待的线程醒来，
     * notifies threads waiting on this object's monitor to wake up             其他线程通过调用 notify 或者 notifyAll 方法。
     * either through a call to the {@code notify} method or the
     * {@code notifyAll} method. The thread then waits until it can            这个线程继续等待直到重新获取了 monitor 的拥有权并重新开始执行。
     * re-obtain ownership of the monitor and resumes execution.
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are     对于单参数版本，中断和虚假唤醒是可能的，
     * possible, and this method should always be used in a loop:              这个方法应该总是在一个循环中被使用，如下
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait();
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * This method should only be called by a thread that is the owner         这个方法应该只被拥有了对象 monitor 的线程去调用。
     * of this object's monitor. See the {@code notify} method for a           看 notify 方法获取一个线程如何成为对象 monitor 的方式的描述。
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of the object's monitor.      如果当前线程未持有对象的 monitor 将会抛出
     * @throws  InterruptedException if any thread interrupted the   任何线程中断了当前线程，在当前线程之前或者当前线程正在等待通知。
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted     这个当前线程的中断标记在抛出异常的时候被清理被重置。
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#notifyAll()
     */
    public final void wait() throws InterruptedException {
        wait(0);
    }
```

### sleep(Thread的方法)
```
 /**
     * Causes the currently executing thread to sleep (temporarily cease     导致当前执行线程睡眠（临时停止执行）一个指定的毫秒数，
     * execution) for the specified number of milliseconds, subject to   
     * the precision and accuracy of system timers and schedulers. The thread   这个和操作系统时间和调度器的精度和准确性有关。
     * does not lose ownership of any monitors.    线程并不会释放 monitor 的拥有权。
     *
     * @param  millis
     *         the length of time to sleep in milliseconds
     *
     * @throws  IllegalArgumentException
     *          if the value of {@code millis} is negative
     *
     * @throws  InterruptedException
     *          if any thread has interrupted the current thread. The
     *          <i>interrupted status</i> of the current thread is
     *          cleared when this exception is thrown.
     */
    public static native void sleep(long millis) throws InterruptedException;
```

+ 要想调用 wait 方法，当前线程必须拥有当前对象的 monitor
+ 一旦调用了 wait 方法之后，当前线程就会释放掉 monitor 的拥有权，将会等待，直到其他线程调用了这个对象的 notify 或者 notifyAll方法去通知
在这个对象的 monitor 上等待的所有线程，然后去重新获取 monitor 的拥有权，获取到之后重新执行代码。
+ 单参数的wait方法应该在一个循环中被调用
+ wait会释放线程对于 monitor 的拥有权，sleep 不会

## notify notifyAll

```
/**
     * Wakes up a single thread that is waiting on this object's                唤醒一个在对象的 monitor 上等待的单一对象。
     * monitor. If any threads are waiting on this object, one of them          如果有任何线程在这个对象等待，
     * is chosen to be awakened. The choice is arbitrary and occurs at         多个线程其中的一个被选择唤醒。  这个选择是任意的并且受实现的约束。
     * the discretion of the implementation. A thread waits on an object's      一个线程通过调用 wait 方法其中的一个来等待对象的 monitor。
     * monitor by calling one of the {@code wait} methods.
     * <p>
     * The awakened thread will not be able to proceed until the current      被唤醒的线程不会继续执行直到当前线程放弃了对象的锁。
     * thread relinquishes the lock on this object. The awakened thread will    
     * compete in the usual manner with any other threads that might be     被唤醒的线程会按照通常的方式和其他线程进行竞争，
     * actively competing to synchronize on this object; for example, the   竞争这个对象的同步，比如，
     * awakened thread enjoys no reliable privilege or disadvantage in being    被唤醒的线程相比其他没有任何的特权和劣势去成为锁住对象的下一个线程。
     * the next thread to lock this object.
     * <p>
     * This method should only be called by a thread that is the owner     这个方法应该仅仅只被拥有了对象 monitor 的线程调用。
     * of this object's monitor. A thread becomes the owner of the         一个线程成为对象 monitor 的持有者有以下三种方式：
     * object's monitor in one of three ways:
     * <ul>
     * <li>By executing a synchronized instance method of that object.      执行这个对象的 synchronized 实例方法。
     * <li>By executing the body of a {@code synchronized} statement       执行锁定这个对象的同步代码块
     *     that synchronizes on the object.
     * <li>For objects of type {@code Class,} by executing a          对于 class 类型对象，通过执行这个 class 的静态 synchronized 方法
     *     synchronized static method of that class.
     * </ul>
     * <p>
     * Only one thread at a time can own an object's monitor.    在一个特定的时间只有一个线程可以拥有对象的 monitor。
     *
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     * @see        java.lang.Object#notifyAll()
     * @see        java.lang.Object#wait()
     */
    public final native void notify();

    /**
     * Wakes up all threads that are waiting on this object's monitor. A     唤醒在这个对象的 monitor 等待的所有线程。
     * thread waits on an object's monitor by calling one of the           一个线程通过调用 wait 方法在对象的 monitor 上等待。
     * {@code wait} methods.
     * <p>
     * The awakened threads will not be able to proceed until the current   被唤醒的线程将不能够执行直到当前线程放弃了对象的锁。
     * thread relinquishes the lock on this object. The awakened threads
     * will compete in the usual manner with any other threads that might   被唤醒线程按照通常的方式和其他线程竞争，
     * be actively competing to synchronize on this object; for example,    竞争对象的同步权。
     * the awakened threads enjoy no reliable privilege or disadvantage in
     * being the next thread to lock this object.
     * <p>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#wait()
     */
    public final native void notifyAll();
```

+ wait
   - 当调用wait时首先需要确保调用wait方法的线程已经持有对象的锁
   - 当调用wait后该线程就会释放掉这个对象的锁让，然后进入到等待队列为等待状态，wait set
   - 当线程调用了wait后进入到等待状态时，就可以通过其他线程调用相同对象的notify和notifyAll方法来使得自己被唤醒
   - 一旦这个线程被其他线程唤醒后，该线程就会和其他线程一同开始竞争这个对象的锁，公平竞争，只有当该线程获取到了这个对象的锁后，线程才会继续往下执行
   - 调用wait方法的代码片段需要放在synchronized块或者synchronized方法中，这样才可以确保线程在调用wait方法前已经获取了对象的锁

+ notify notifyAll
   - 当调用对象的notify方法时，他会随机唤醒该对象等待集合wait set中的任意一个线程，当某个线程被唤醒后，他就会与其他线程一同竞争对象的锁
   - 当调用对象的notifyAll时，会唤醒该对象等待集合中的所有线程，这些线程被唤醒后，会同时竞争该对象上的锁
   - 在某一个时刻，只有唯一一个线程可以拥有对象的锁