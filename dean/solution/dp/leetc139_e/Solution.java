package dp.leetc139_e;

import java.util.*;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        //dp[i]从0到i能否组成dict words
        //dp[i]=dp[i] || (dp[j] && wordDict.contains(s.substring(j+1,i)))
        //                  {j:0->i}
        Set<String> dict = new HashSet<>();
        for(int i=0;i<wordDict.size();i++){
            dict.add(wordDict.get(i));
        }
        
        
        boolean[] dp = new boolean[s.length()+5];
        if(dict.contains(s.substring(0,1))){
            dp[0]=true;
        } else {
            dp[0]=false;
        }
        
        for(int i=1;i<s.length();i++){
            for(int j=0;j<=i;j++){
                if(j!=0){
                    dp[i] = dp[i] || (dp[j-1]&&dict.contains(s.substring(j,i+1)));
                } else{
                    dp[i] = dp[i] || dict.contains(s.substring(j,i+1));
                }
                
            }
        }
        
        return dp[s.length()-1];
    }
}
