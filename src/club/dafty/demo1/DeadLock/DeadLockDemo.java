package club.dafty.demo1.DeadLock;

/**
 * @author leechengchao@foxmail.com
 * @version 1.0
 * @date 2019/5/28 11:37
 */
public class DeadLockDemo implements Runnable{
    public String getLockA() {
        return lockA;
    }

    public String getLockB() {
        return lockB;
    }

    String lockA ;
    String lockB ;

    public DeadLockDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 等待获得"+lockB);
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t "+lockB+"执行");
            }
        }
    }

    public static void main(String[] args) {
        String lockA = "锁1";
        String lockB = "锁2";
        new Thread((new DeadLockDemo(lockA,lockB)),"threadA").start();
        new Thread((new DeadLockDemo(lockB,lockA)),"threadB").start();
    }
}
