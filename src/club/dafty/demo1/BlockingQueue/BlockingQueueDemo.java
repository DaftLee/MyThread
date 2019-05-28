package club.dafty.demo1.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lichengchao
 * @email leechengchao@foxmail.com
 * @date 2019/5/14 15:11
 *
 * 阻塞队列
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue(3);
        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue(3);
        BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue(3);
        BlockingQueue<String> blockingQueue4 = new ArrayBlockingQueue(3);
/*
        //*************抛出异常*****************
        System.out.println(blockingQueue1.add("a"));
        System.out.println(blockingQueue1.add("b"));
        System.out.println(blockingQueue1.add("c"));
        System.out.println(blockingQueue1.add("x")); //java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue1.element()); //a
        System.out.println(blockingQueue1.remove());
        System.out.println(blockingQueue1.remove());
        System.out.println(blockingQueue1.remove());
        System.out.println(blockingQueue1.remove()); //java.util.NoSuchElementException
*/
/*
        //*************特殊值*****************
        System.out.println(blockingQueue2.offer("a"));
        System.out.println(blockingQueue2.offer("b"));
        System.out.println(blockingQueue2.offer("c"));
        System.out.println(blockingQueue2.offer("x")); //false

        System.out.println(blockingQueue2.peek()); //a

        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll()); //null
*/

/*
        //**************阻塞***************
        blockingQueue3.put("a");
        blockingQueue3.put("b");
        blockingQueue3.put("c");
        blockingQueue3.put("x");

        blockingQueue3.take();
        blockingQueue3.take();
        blockingQueue3.take();
        blockingQueue3.take();
*/

        //超时
        System.out.println(blockingQueue4.offer("a",2, TimeUnit.SECONDS));
        System.out.println(blockingQueue4.offer("b",2, TimeUnit.SECONDS));
        System.out.println(blockingQueue4.offer("c",2, TimeUnit.SECONDS));
        System.out.println(blockingQueue4.offer("x",2, TimeUnit.SECONDS));//阻塞2s后输出false

        System.out.println(blockingQueue4.poll(2,TimeUnit.SECONDS));
        System.out.println(blockingQueue4.poll(2,TimeUnit.SECONDS));
        System.out.println(blockingQueue4.poll(2,TimeUnit.SECONDS));
        System.out.println(blockingQueue4.poll(2,TimeUnit.SECONDS));//阻塞2s后输出null
    }
}
