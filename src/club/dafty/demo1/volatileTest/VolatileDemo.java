package club.dafty.demo1.volatileTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    public static void main(String[] args) {

//        seeOkByVolatile();
        noAtomicVolatile();

    }
    /**
     * 验证volatile可见性
     */
    public static void seeOkByVolatile(){
        Mydata mydata = new Mydata();
        System.out.println(mydata.number);
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"变更前-"+mydata.number);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mydata.updateNumber();
            System.out.println(Thread.currentThread().getName()+"变更后-"+mydata.number);
        },"Thread1").start();
        while (mydata.number == 0){} //值未修改就一直在这循环，如果修改了则结束循环，从而验证main线程对Thread1线程的修改number值可见
        System.out.println(Thread.currentThread().getName()+"当前值-"+mydata.number);

    }
    /**
     * 验证volatile不保证原子性
     *  解决方案：1. 加synconized
     *           2. JUC的类
     */
    public static void noAtomicVolatile(){
        Mydata mydata = new Mydata();
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                for (int j = 0; j < 1000; j++) {
                    mydata.addNumber();
                    mydata.addNumberAtomic();
                }
            },"Thread1").start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(mydata.number);
        System.out.println(mydata.atomicInteger);
    }
}

class Mydata{
    volatile int number = 0;
    public void updateNumber(){
        this.number = 60;
    }
    //普通类型自增
    public /* synchronized */ void addNumber(){
        this.number++;
    }

    //JUC类型自增
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addNumberAtomic(){
        atomicInteger.getAndIncrement();
    }

}
