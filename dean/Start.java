import two_pointer.lc475.Solution;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Start {
    static int counter = 0;
    static ReentrantLock lock = new ReentrantLock();

    //    public static void main(String[] args) throws InterruptedException {
//
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        ExecutorService service2 = Executors.newSingleThreadExecutor();
//        service.submit(()->{
//            for(int i=0;i<20;i++){
//                if(lock.tryLock()){
//                    System.out.println("Hello1 "+counter++);
//                    lock.unlock();
//                }
//
//            }
//        });
//        TreeMap<>
//        service2.submit(()->{
//            for(int i=0;i<20;i++){
//                if(lock.tryLock()) {
//                    System.out.println("Hello2 " + counter++);
//                    lock.unlock();
//                }
//            }
//        });
//        Thread.sleep(3000);
//        System.out.println("Shutdown");
//        service.shutdownNow();
//        service2.shutdownNow();
//    }
//    static class Counter {
//        String name;
//        int count;
//        Counter(String name,int count){
//            this.name = name;
//            this.count = count;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            Counter c = (Counter) obj;
//            return c.name.equals(this.name) && c.count==this.count;
//        }
//
//        @Override
//        public String toString() {
//            return name+":"+count;
//        }
//    }
//
    public static void main(String[] args) throws InterruptedException {
        DFS(new StringBuilder("abcddd"));
    }
    static void DFS(StringBuilder sb){
        while(sb.length()!=0){
            System.out.println(sb.toString());
            if(sb.charAt(0)=='c'){
                sb.deleteCharAt(0);
                return;
            }
            DFS(sb.deleteCharAt(0));
        }
    }
}
