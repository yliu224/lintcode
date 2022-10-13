package string.lc1152;

import java.util.*;

public class Solution {
    static class Record implements Comparable<Record>{
        String web;
        int time;

        Record(String web,int time){
            this.web = web;
            this.time = time;
        }

        @Override
        public int compareTo(Record d){
            //1,2,3
            return this.time-d.time;
        }

    }
    static class Pattern implements Comparable<Pattern>{
        String pattern;
        int frequency;

        Pattern(String pattern,int frequency){
            this.pattern = pattern;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Pattern p){
            if(this.frequency==p.frequency){
                //a,b,c
                return this.pattern.compareTo(p.pattern);
            }
            //3,2,1
            return p.frequency-this.frequency;
        }

    }
    private Map<String,Pattern> patterns = new HashMap<>();
    private Map<String,List<Record>> userRecord = new HashMap<>();

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        buildUserRecord(username,timestamp,website);
        buildPatterns();
        return findPatterns();
    }

    private void buildUserRecord(String[] username, int[] timestamp, String[] website){
        for(int i=0;i<username.length;i++){
            userRecord.computeIfAbsent(username[i],x->new ArrayList<>())
                    .add(new Record(website[i], timestamp[i]));
        }
        for(List<Record> records:userRecord.values()){
            Collections.sort(records);
        }
    }

    private void buildPatterns(){
        Map<String,Integer> p = new HashMap<>();
        for(List<Record> records:userRecord.values()){
            p.clear();
            for(int i=0;i<records.size()-2;i++){
                for(int j=i+1;j<records.size()-1;j++){
                    for(int m=j+1;m<records.size();m++){
                        p.put(records.get(i).web+","+records.get(j).web+","+records.get(m).web,1);
                    }
                }
            }

            p.forEach((k,v)->{
                patterns.computeIfAbsent(k,kk->new Pattern(kk,0)).frequency+=1;
            });
        }
    }

    private List<String> findPatterns(){
        List<Pattern> candidate = new ArrayList<>(patterns.values());
        Collections.sort(candidate);
        return Arrays.stream(candidate.get(0).pattern.split(",")).toList();
    }
}
