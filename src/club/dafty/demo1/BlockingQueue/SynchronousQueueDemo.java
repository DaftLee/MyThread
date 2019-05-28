package club.dafty.demo1.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lichengchao
 * @email leechengchao@foxmail.com
 * @date 2019/5/15 17:12
 * SynchronousQueue没有容量：
 *
 * 与其他BlockingQueue不同，此种阻塞队列一个不存储元素的BlockingQueue；
 *
 * 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName()+":put - a");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName()+":put - b");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName()+":put - c");
                blockingQueue.put("c");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"ThreadA").start();
        new Thread(() ->{
            try {
                try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+":take - "+blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+":take - "+blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+":take - "+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"ThreadB").start();
    }
}
