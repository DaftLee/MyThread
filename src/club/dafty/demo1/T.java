package club.dafty.demo1;

public class T implements Runnable {
    int count = 10;
    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+"count="+count);
    }

    public static void main(String[] args) {
//        T t = new T();
//        for (int i = 0; i < 100; i++) {
//            new Thread(t,"Thread-"+i).start();
//        }
        long a = Long.parseLong("500");
        System.out.println(String.format("%.2f",(double)a /1024));

        System.out.println(String.format("%.2f",
                Double.parseDouble("12521") / 100));
    }
}
