package bfs.leetc87_h;

import java.util.*;

public class Solution {
    public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2)) return true;
        Queue<String> q = new LinkedList<>();
        Queue<String> cacheQ = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        q.add(s1);
        visited.add(s1);
        
        while(!q.isEmpty()){
            while(!q.isEmpty()){
                String curStr = q.poll();
                String[] str = curStr.split(":");
                
                for(int i=0;i<str.length;i++){
                    for(int j=1;j<str[i].length();j++){
                        String newCutStrList = getNextCut(str,i,j);
                        String newCutStrListSwapped = getNextCutSwapped(str,i,j);
                        String newCutStr = getString(newCutStrList);
                        String newCutStrSwapped = getString(newCutStrListSwapped);
                        System.out.println(newCutStr);
                        System.out.println(newCutStrSwapped);
                        if(newCutStr.equals(s2) || newCutStrSwapped.equals(s2)){
                            return true;
                        }
                        if(!visited.contains(newCutStrList)){
                            visited.add(newCutStrList);
                            cacheQ.add(newCutStrList);
                        }
                        if(!visited.contains(newCutStrListSwapped)){
                            visited.add(newCutStrListSwapped);
                            cacheQ.add(newCutStrListSwapped);
                        }
                    }
                }
                
            }
            System.out.println();
            q.addAll(cacheQ);
            cacheQ.clear();
        }
        return false;   
    }
    
    private String getString(String str){
        StringBuilder sb = new StringBuilder(str);
        int i=0;
        while(i<sb.length()){
            if(sb.charAt(i)==':'){
                sb.deleteCharAt(i);
            } else{
                i++;
            }
        }
        return sb.toString();
    }

    private String getNextCut(String[] str, int i, int j){
        StringBuilder sb = new StringBuilder();
        for(int index=0;index<str.length;index++){
            if(index==i){
                sb.append(str[index].substring(0,j)+":");
                sb.append(str[index].substring(j)+":");
            } else{
                sb.append(str[index]+":");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    private String getNextCutSwapped(String[] str, int i, int j){
        StringBuilder sb = new StringBuilder();
        for(int index=0;index<str.length;index++){
            if(index==i){
                sb.append(str[index].substring(j)+":");
                sb.append(str[index].substring(0,j)+":");    
            } else{
                sb.append(str[index]+":");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
