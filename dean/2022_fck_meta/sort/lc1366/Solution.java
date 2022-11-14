package sort.lc1366;

import java.util.*;

public class Solution {
    class Team{
        char name;
        int[] vote;
        Team(char name,int len){
            this.name = name;
            this.vote = new int[len];
        }
    }
    public String rankTeams(String[] votes) {
        Map<Character,Team> map = new HashMap<>();
        for(String v:votes){
            int score = 0;
            for(char c:v.toCharArray()){
                map.computeIfAbsent(c,x->new Team(x,v.length())).vote[score]++;
                score++;
            }
        }

        PriorityQueue<Team> pq = new PriorityQueue<>((a,b)->{
            //注意审题
            for(int i=0;i<a.vote.length;i++){
                if(a.vote[i]!=b.vote[i]){
                    return b.vote[i]-a.vote[i];
                }
            }
            return a.name-b.name;
        });
        for(Team t:map.values()){
            pq.offer(t);
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            sb.append(pq.poll().name);
        }
        return sb.toString();
    }
}
