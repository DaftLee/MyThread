package club.dafty.demo1.JUCTools;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author lichengchao
 * @email leechengchao@foxmail.com
 * @date 2019/5/13 17:12
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //理解为3个车位
        for (int i = 0; i < 6; i++) {
            new Thread(() ->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    try { TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                    semaphore.release();
                }
            },"Thread"+i).start();
        }
    }
}
