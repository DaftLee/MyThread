package club.dafty.demo1.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicThread = new AtomicReference<>();

    //lock，只有当atomicThread对象为空，才能获得锁（即将当前线程赋给atomicThread）
    public void myLock() {
        Thread t = Thread.currentThread();
        System.out.println(t.getName()+"ready to lock");
        while (!atomicThread.compareAndSet(null,t)){
        }
    }

    //unlock，将atomicThread对象置空，使后面进来的线程能够进行获取锁（即满足atomicThread对象为空）
    public void myUnlock(){
        Thread t = Thread.currentThread();
        atomicThread.compareAndSet(t,null);
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        //Thread1占用锁
        new Thread(() ->{
            spinLockDemo.myLock();
            System.out.println("Thread1 lock ...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
            System.out.println("Thread1 unlock ...");
        },"Thread1").start();

        //Thread2占用锁
        new Thread(() ->{
            spinLockDemo.myLock();
            System.out.println("Thread2 lock ...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
            System.out.println("Thread2 unlock ...");
        },"Thread2").start();
    }

}
