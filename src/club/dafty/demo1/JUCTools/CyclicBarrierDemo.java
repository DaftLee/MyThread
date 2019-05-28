package club.dafty.demo1.JUCTools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lichengchao
 * @email leechengchao@foxmail.com
 * @date 2019/5/13 16:38
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });

        for (int i = 0; i < 7; i++) {
            int finalI = i;
            new Thread(() ->{
                System.out.println("集齐第"+ finalI +"龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"Thread"+i).start();
        }
    }
}
