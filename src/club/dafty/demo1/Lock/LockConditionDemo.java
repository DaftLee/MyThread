package club.dafty.demo1.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author leechengchao@foxmail.com
 * @version 1.0
 * @date 2019/5/19 21:43
 * 验证Condition的精准唤醒
 * A打印5次，B打印10次，C打印15次，循环进行，打印10轮
 */
public class LockConditionDemo {
    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();
    /**
     * A为1，B为2，C为3
     */
    private int number = 1;

    public void printA(){
        lock.lock();
        try {
          //判断
          while (number !=1){
              c1.await();
          }
          //操作
          number ++;
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
          //唤醒
          c2.signal();
        } catch (Exception e){
            e.printStackTrace();
        }
        lock.unlock();
    }
    public void printB(){
        lock.lock();
        try {
            //判断
            while (number !=2){
                c2.await();
            }
            //操作
            number ++;
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //唤醒
            c3.signal();
        } catch (Exception e){
            e.printStackTrace();
        }
        lock.unlock();
    }
    public void printC(){
        lock.lock();
        try {
            //判断
            while (number !=3){
                c3.await();
            }
            //操作
            number = 1;
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //唤醒
            c1.signal();
        } catch (Exception e){
            e.printStackTrace();
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        LockConditionDemo conditionDemo = new LockConditionDemo();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                conditionDemo.printA();
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                conditionDemo.printB();
            }
        },"B").start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                conditionDemo.printC();
            }
        },"C").start();
    }
}
