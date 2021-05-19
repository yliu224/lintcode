package two_pointer.lc295_m;

import java.util.*;

public class Solution {
    /**
     * @param a: first sequence
     * @param b: second sequence
     * @return: return ans
     */
    public List<List<Integer>> Intersection(List<List<Integer>> a, List<List<Integer>> b) {


        List<List<Integer>> intersection = new ArrayList<>();

        int i=0;
        int j=0;
        while(i<a.size()-1 || j<b.size()-1){
            List<Integer> ai=a.get(i);
            List<Integer> bj=b.get(j);
            if(isIntersect(ai,bj)){
                intersection.add(new ArrayList<>(Arrays.asList(i,j)));
            }

            if(isGreater(ai,bj)){
                if(j!=b.size()-1){
                    j++;
                } else{
                    i++;//注意这儿
                }
            } else{
                if(i!=a.size()-1){
                    i++;
                } else{
                    j++;//注意这儿
                }
            }
        }

        if(isIntersect(a.get(i),b.get(j))){//注意这儿
            intersection.add(new ArrayList<>(Arrays.asList(i,j)));
        }

        return intersection;
    }

    private boolean isGreater(List<Integer> a,List<Integer> b){
        int endA = a.get(1);
        int endB = b.get(1);

        return endA>=endB;
    }

    private boolean isIntersect(List<Integer> a, List<Integer> b){
        int startA = a.get(0);
        int endA = a.get(1);
        int startB = b.get(0);
        int endB = b.get(1);

        if(startA>=startB && startA<=endB){
            return true;
        }

        if(endA>=startB && endA<=endB){
            return true;
        }

        if(startB>=startA && startB<=endA){
            return true;
        }

        if(endB>=startA && endB<=endA){
            return true;
        }

        return false;
    }
}
