package club.dafty.demo1.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class NofairLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock  = new ReentrantLock(false);
    }
}
