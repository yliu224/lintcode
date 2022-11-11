package dfs.lc78;

import java.util.*;

public class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        result.add(new ArrayList<>());
        DFS(nums,0,new LinkedList<>());
        return result;
    }

    private void DFS(int[] nums,int index,LinkedList<Integer> r){
        for(int i=index;i<nums.length;i++){
            r.addLast(nums[i]);
            result.add(new ArrayList<>(r));
            DFS(nums,i+1,r);
            r.removeLast();
        }
    }
}
