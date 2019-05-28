package club.dafty.demo1.CASTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS：比较并交换 compare and swap
 */
public class CASDemo {
    public static void main(String[] args) {
        //将5写入主内存
        AtomicInteger atomicInteger = new AtomicInteger(5);

        //比较期望值expect（主内存）是否为5，是的话修改为10
        System.out.println("修改结果："+atomicInteger.compareAndSet(5,10)+" -- "+"当前值："+atomicInteger.get());

        //比较期望值expect（主内存）是否为5，是的话修改为12
        System.out.println("修改结果："+atomicInteger.compareAndSet(5,12)+" -- "+"当前值："+atomicInteger.get());

    }
}
