package dp.leetc97_h;

public class Solution {
    private boolean isFound = false;

    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) return false;
        if(s1.length()==0 || s2.length()==0 || s3.length()==0){
            return validateSpecialCase(s1,s2,s3);
        }
        
        boolean[][] dp = new boolean[s1.length()+5][s2.length()+5];
        //dp[i][j]表示，s1[0:i]，s2[0:j]能否组成满足条件的s3[0:(i+j)]
        //dp[i][j]=(dp[i-1][j] && (s3[i+j-1]==s1[i-1])) || (dp[i][j-1] && (s3[i+j-1]==s2[j-1]))
        //return dp[s1.length()][s2.length()]
        //注意这儿的定义，i，j 表示长度
        dp[0][0]=true;
        for(int i=0;i<=s1.length();i++){
            for(int j=0;j<=s2.length();j++){
                if(i==0 && j==0){
                    continue;
                }
                if(i-1>=0){
                    dp[i][j]=dp[i-1][j] && s3.charAt(i+j-1)==s1.charAt(i-1);
                }
                if(j-1>=0){
                    dp[i][j]=dp[i][j] || (dp[i][j-1] && s3.charAt(i+j-1)==s2.charAt(j-1));
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
    
    private boolean validateSpecialCase(String s1,String s2,String s3){
        if(s1.length()==0 && s2.length()==0 && s3.length()==0) return true;
        
        if(s1.length()==0){
            for(int i=0;i<s2.length();i++){
                if(s2.charAt(i)!=s3.charAt(i)){
                    return false;
                }
            }
            
        }
        
        if(s2.length()==0){
            for(int i=0;i<s1.length();i++){
                if(s1.charAt(i)!=s3.charAt(i)){
                    return false;
                }
            }
        }
        return true;
        
    }

    // public boolean isInterleave(String s1, String s2, String s3) {
    //     StringBuilder sb1 = new StringBuilder(s1);
    //     StringBuilder sb2 = new StringBuilder(s2);
    //     StringBuilder sb3 = new StringBuilder(s3);
    //     DFS(sb1,sb2,sb3);
    //     return isFound;
    // }
    // ********TLE*********
    private void DFS(StringBuilder s1,StringBuilder s2,StringBuilder s3){
        if(isFound){
            return;
        }
        if((s1.length()==0 || s2.length()==0)&&s3.length()==0){ 
            if(s1.length()==0 && s2.length()==0){
                isFound=true;
            }
            return;
        }
        if(s1.length()!=0){
            if(s1.charAt(0)==s3.charAt(0)){
                char c1 = s1.charAt(0);
                char c3 = s3.charAt(0);
                s1.deleteCharAt(0);
                s3.deleteCharAt(0);
                DFS(s1,s2,s3);
                s1.insert(0, c1);
                s3.insert(0, c3);
            }
        }
        if(s2.length()!=0){
            if(s2.charAt(0)==s3.charAt(0)){
                char c2 = s2.charAt(0);
                char c3 = s3.charAt(0);
                s2.deleteCharAt(0);
                s3.deleteCharAt(0);
                DFS(s1,s2,s3);
                s2.insert(0, c2);
                s3.insert(0, c3);
            }
        }
    }
}
