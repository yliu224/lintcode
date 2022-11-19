package dfs.lc1238;

import java.util.*;

public class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new LinkedList<>();
        visited.add(start);
        path.add(start);
        return DFS(n,start,visited,path);
    }

    private List<Integer> DFS(int n,int cur,Set<Integer> visited,List<Integer> path){
        if(path.size()==Math.pow(2,n)){
            return new ArrayList<>(path);
        }
        int s = 1;
        for(int i=0;i<n;i++){
            int next = cur ^ s;
            if(!visited.contains(next)){
                visited.add(next);
                path.add(next);
                List<Integer> ans = DFS(n,next,visited,path);
                if(ans!=null){
                    return ans;
                }
                path.remove(next);
                visited.remove(next);
            }
            s = s<<1;
        }
        return null;
    }
}
