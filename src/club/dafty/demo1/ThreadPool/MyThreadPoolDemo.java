package club.dafty.demo1.ThreadPool;

import java.util.concurrent.*;

/**
 * @author leechengchao@foxmail.com
 * @version 1.0
 * @date 2019/5/27 9:51
 * 第4种获得多线程方式、线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 5;
        long keepAliveTime = 1L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue workQueue = new LinkedBlockingQueue<Runnable>(3);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        ExecutorService threadPool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory,handler);

        //模拟10个用户请求办理业务，每个用户就是一个来自外部请求线程
        try {
            for (int i = 0; i < 9; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });

            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * JDK自带的Executors创建，不建议
     */
    private static void threadJDKinit() {
        //1池多线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //1池1线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //1池N线程
        //ExecutorService threadPool = Executors.newCachedThreadPool();

        //模拟10个用户请求办理业务，每个用户就是一个来自外部请求线程
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
                try { TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
