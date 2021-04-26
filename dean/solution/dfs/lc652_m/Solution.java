package dfs.lc652_m;

import java.util.ArrayList;
import java.util.List;

//TLE
public class Solution {
    /**
     * @param n: An integer
     * @return: a list of combination
     */
    private List<List<Integer>> results = new ArrayList<>();
    public List<List<Integer>> getFactors(int n) {
        for(int i=2;i<=Math.sqrt(n);i++){
            printFactors(i,n,new ArrayList<>());
        }
        return results;
    }

    private void printFactors(int factor,int n,List<Integer> factors){
        if(n<factor) return;
        if(n%factor!=0) return;
        if(n/factor==1){
            if(factors.size()==0) return;
            List<Integer> newFactorSet = new ArrayList<>(factors);
            newFactorSet.add(factor);
            results.add(newFactorSet);
            return;
        }
        factors.add(factor);
        for(int i=factor;i<=n;i++){
            printFactors(i,n/factor,factors);
        }
        factors.remove(factors.size()-1);
    }
}

