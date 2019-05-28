package club.dafty.demo1.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author leechengchao@foxmail.com
 * @version 1.0
 * @date 2019/5/20 0:05
 */
public class ProdConsumerBlocingQDemo {
    private volatile boolean FLAG = true;
    private AtomicInteger number = new AtomicInteger(1);
    BlockingQueue<String> blockingQueue;

    public ProdConsumerBlocingQDemo(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    /**
     * 生产者
     * @throws InterruptedException
     */
    public void producer() throws InterruptedException {
        String snum ;
        //当FLAG为false时跳出循环，通过volatile保证可见性
        while (FLAG){

            //用AtomicInteger的方法保证原子性，i++
            snum = number.getAndIncrement()+"";
            blockingQueue.offer(snum,2L, TimeUnit.SECONDS);

            System.out.println(Thread.currentThread().getName()+"\t"+"生产成功:"+snum);
            //隔一秒生产一个
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        }
        System.out.println("生产终止");
    }

    /**
     * 消费者
     * @throws InterruptedException
     */
    public void consumer() throws InterruptedException {
        String snum;
        //用“OK”做标志，便于后面continue跳出指定循环
        OK:
        while (FLAG){
            snum = blockingQueue.poll(2L,TimeUnit.SECONDS);
            //多线程高并发用while替代if
            while (snum == null|| snum.equalsIgnoreCase("")){
                System.out.println(Thread.currentThread().getName()+"\t"+"消费失败");
                continue OK;
            }
            System.out.println(Thread.currentThread().getName()+"\t"+"消费成功:"+snum);

        }
        System.out.println("消费终止");
    }

    /**
     * 通过标志FLAG为false停止生产和消费
     */
    public void stopall(){
        FLAG = false;
        System.out.println("stopall!!!");
    }

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        ProdConsumerBlocingQDemo prodConsumerBlocingQDemo = new ProdConsumerBlocingQDemo(blockingQueue);
        //生产者
        new Thread(() ->{
            try {
                prodConsumerBlocingQDemo.producer();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"producer").start();

        //消费者
        new Thread(() ->{
            try {
                prodConsumerBlocingQDemo.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer").start();

        //主线程运行10秒后停止生产和消费
        try {TimeUnit.SECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}

        prodConsumerBlocingQDemo.stopall();
    }
}
