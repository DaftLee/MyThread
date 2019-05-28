package club.dafty.demo1.ContainerNotSafeTest;

import java.util.*;

/**
 * 集合不安全类问题
 */
public class ContainerNotSaveDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();                                   //解决方案1：用Vector代替List
//        List<String> list = Collections.synchronizedList(new ArrayList<>());  //解决方案2：用Collections.synchronizedList加锁
        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },"Thread"+i).start();
        }
//        java.util.ConcurrentModificationException 并发修改异常
    }
}
