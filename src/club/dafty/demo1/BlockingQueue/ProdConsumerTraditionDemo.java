package club.dafty.demo1.BlockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author leechengchao@foxmail.com
 * @version 1.0
 * @date 2019/5/15 17:43
 * 生产者消费者模式
 * 模拟5次增加、减少数字交替进行
 *
 * 线程 操作 资源类
 * 判断 操作 唤醒
 * 注意多线程虚假唤醒，用while替代if.
 */
public class ProdConsumerTraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        //A线程增加数字
        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increaseNum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"A").start();

        //B线程减少数字
        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decreaseNum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"B").start();
    }
}

/**
 * 资源类
 */
class ShareData{
    private int flag = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //数字++
    public void increaseNum() throws InterruptedException {
        //同syncronized锁住代码块
        lock.lock();
        try {
            //多线程情况下建议用while而不用if，原因是如果是唤醒if后的进程后不会再判断，直接执行唤醒后的阻塞操作即flag++。防止虚假唤醒
            while (flag != 0){
                //同sycronized锁住的对象.wait()
                condition.await();
            }
            flag++;
            System.out.println(Thread.currentThread().getName()+" - increaseNum,current num is:"+flag);
            //同sycrnized锁住的对象.notifyAll()
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        }
        lock.unlock();

    }
    //数字--
    public void decreaseNum() throws InterruptedException {
        lock.lock();
        try {
            while (flag == 0){
                condition.await();
            }
            flag--;
            System.out.println(Thread.currentThread().getName()+" - decreaseNum,current num is:"+flag);
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        }
        lock.unlock();

    }
}