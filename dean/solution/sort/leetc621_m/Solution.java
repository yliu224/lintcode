package sort.leetc621_m;

import java.util.*;

public class Solution {
    class Task{
        public char id;
        public int count;
        public Task(char id,int count){
            this.id = id;
            this.count = count;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        Map<Character,Integer> countTasks = new HashMap<>();
        PriorityQueue<Task> pending = new PriorityQueue<>((a,b)->b.count-a.count);
        Set<Character> executingSet= new HashSet<>();
        Deque<Character> executingQ = new LinkedList<>();
        int totalCount = 0;

        for(int i=0;i<tasks.length;i++){
            countTasks.put(tasks[i],countTasks.getOrDefault(tasks[i], 0)+1);
            totalCount++;
        }

        for(Map.Entry<Character,Integer> task:countTasks.entrySet()){
            pending.add(new Task(task.getKey(),task.getValue()));
        }

        int clock = 0;
        boolean isIdle = true;
        while(totalCount>0){
            isIdle = true;
            Task executingTask = null;
            Iterator<Task> tIterator = pending.iterator();
            while(tIterator.hasNext()){
                Task t = tIterator.next();
                if(!executingSet.contains(t.id) && t.count!=0){
                    executingTask = t;
                    executingSet.add(t.id);
                    executingQ.addLast(t.id);
                    t.count--;
                    totalCount--;
                    isIdle=false;
                    break;
                }
            }
            if(isIdle){
                executingQ.addLast('-');
            } else{
                //PriorityQueue的update，记住！！！！很重要
                pending.remove(executingTask);
                pending.add(executingTask);
            }
            if(executingQ.size()>n){
                char id = executingQ.pollFirst();
                if(id!='-'){
                    executingSet.remove(id);
                }
            }
            clock++;
        }

        return clock;
    }
}
