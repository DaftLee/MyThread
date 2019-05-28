package club.dafty.demo1.Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lichengchao
 * @email leechengchao@foxmail.com
 * @date 2019/5/13 11:46
 */
public class ReadWriteLockDemo {
    private volatile Map<String,String> map = new HashMap<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //写入，用 写锁/独占锁 锁住
    public void put(String key,String value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println("准备写入"+key);
            map.put(key,value);
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println("写入成功"+key);
        } catch (Exception e){
            e.printStackTrace();
        }
        readWriteLock.writeLock().unlock();

    }
    //读取，用 读锁/共享锁 锁住
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println("准备读取"+key);
            String value = map.get(key);
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println("读取成功"+key+"-"+value);
        } catch (Exception e){
            e.printStackTrace();
        }
        readWriteLock.readLock().unlock();

    }

    public static void main(String[] args) {
        ReadWriteLockDemo rtlock = new ReadWriteLockDemo();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() ->{
                rtlock.put(finalI +"", finalI +"");
            },"Thread"+i).start();
        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() ->{
                rtlock.get(finalI +"");
            },"Thread"+"i").start();
        }
    }
}
