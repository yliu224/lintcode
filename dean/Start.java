import two_pointer.lc475.Solution;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Start {
    static int counter = 0;
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> seed = new LinkedBlockingQueue<>();
    }
    static void DFS(StringBuilder sb){
        List<Object> ls = new ArrayList<>();
        ls.stream().mapToInt(x-> (int) x).sum();
        while(sb.length()!=0){
            Set<String> dedup = ConcurrentHashMap.newKeySet();
            System.out.println(sb.toString());
            if(sb.charAt(0)=='c'){
                sb.deleteCharAt(0);
                return;
            }
            DFS(sb.deleteCharAt(0));
        }
    }
}
