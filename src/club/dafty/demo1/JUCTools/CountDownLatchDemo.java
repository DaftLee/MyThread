package club.dafty.demo1.JUCTools;

import java.util.concurrent.CountDownLatch;

/**
 * @author lichengchao
 * @email leechengchao@foxmail.com
 * @date 2019/5/13 16:02
 */
public class CountDownLatchDemo {

    public static void myCountDownLatch () {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            new Thread(() ->{
                System.out.println(finalI +"同学离开教室");
                countDownLatch.countDown();
            },"Thread"+i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("班长锁门离开教室");
    }

    public static void main(String[] args) {
        myCountDownLatch();
    }
}
