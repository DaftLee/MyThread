package club.dafty.demo1.volatileTest;

public class SingletonDemo {
    private static volatile SingletonDemo singletonDemo;

    public SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 初始化SingletonDemo");
    }

    //DCL
    public static SingletonDemo getInstance(){
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class){
                if (singletonDemo == null){
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            },"Thread").start();
        }
    }

}
