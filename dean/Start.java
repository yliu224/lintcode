import two_pointer.lc475.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Start {
    static int counter = 0;
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        ExecutorService service2 = Executors.newSingleThreadExecutor();
        service.submit(()->{
            for(int i=0;i<20;i++){
                if(lock.tryLock()){
                    System.out.println("Hello1 "+counter++);
                    lock.unlock();
                }

            }
        });
        service2.submit(()->{
            for(int i=0;i<20;i++){
                if(lock.tryLock()) {
                    System.out.println("Hello2 " + counter++);
                    lock.unlock();
                }
            }
        });
        Thread.sleep(3000);
        System.out.println("Shutdown");
        service.shutdownNow();
        service2.shutdownNow();
    }
}
