package dp.lc1565_m;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution{
    /**
     * @param length: the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public int modernLudo(int length, int[][] connections) {
        //connection 的end 可能有重复的
        //dp[i]=Math.min(dp[i-1]+1,dp[i-2]+1,dp[i-3]+1,dp[i-4]+1,dp[i-5]+1,dp[i-6]+1,dp[connection])
        //Build Connection map
        Map<Integer,List<Integer>> connectionMap = new HashMap<>();
        Map<Integer,Integer> shortestConnection = new HashMap<>();

        for(int i=0;i<connections.length;i++){
            int end = connections[i][1];
            int start = connections[i][0];
            if(connectionMap.containsKey(end)){
                List<Integer> connectionNodes = connectionMap.get(end);
                connectionNodes.add(start);
                connectionMap.put(end,connectionNodes);
            } else{
                //https://stackoverflow.com/questions/5755477/java-list-add-unsupportedoperationexception
                //connectionMap.put(end,Arrays.asList(start));
                connectionMap.put(end,new ArrayList<Integer>(Arrays.asList(start)));
            }
            shortestConnection.put(start,Integer.MAX_VALUE);
        }

        int[] dp = new int[7];
        dp[0]=0;
        dp[1]=0;
        for(int i=2;i<=length;i++){
            int minStep=Integer.MAX_VALUE;
            if(connectionMap.containsKey(i)){
                for(int index:connectionMap.get(i)){
                    minStep=Math.min(minStep,shortestConnection.get(index));
                }
            }
            for(int dice=1;dice<=6;dice++){
                if(i-dice>=0){
                    minStep=Math.min(minStep,dp[(i-dice)%6]+1);
                }
            }
            dp[i%6]=minStep;
            if(shortestConnection.containsKey(i)){
                shortestConnection.put(i,Math.min(shortestConnection.get(i),minStep));
            }
        }
        return dp[length%6];
    }
}