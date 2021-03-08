package dean.solution.lc1850_m;

import java.util.Arrays;

public class Solution {
    public int PickApples(int[] A, int K, int L) {
        // write your code here
        if(K+L>A.length) return -1;
        int[] prefixSum = getPrefixSum(A);

        int[] sumOfWindowK = getSumOfWindowN(prefixSum, K);
        int[] sumOfWindowL = getSumOfWindowN(prefixSum, L);

        //住一个这个数组的定义
        //maxOfLeftK[i]表示当在第i个位置，左边window size 为 K 的 Sum的最大值
        int[] maxOfLeftK = getMaxElementInArray(sumOfWindowK,K,false);
        int[] maxOfRightK = getMaxElementInArray(sumOfWindowK,K,true);
        int[] maxOfLeftL = getMaxElementInArray(sumOfWindowL,L,false);
        int[] maxOfRightL = getMaxElementInArray(sumOfWindowL,L,true);

        int max=Integer.MIN_VALUE;
        for(int i=0;i<A.length-1;i++){

            max = Math.max(maxOfLeftK[i]+maxOfRightL[i+1],max);
            max = Math.max(maxOfLeftL[i]+maxOfRightK[i+1],max);
        }
        return max;
    }

    private int[] getMaxElementInArray(int[] array,int len,boolean isReverseOrder){
        int[] max = new int[array.length];
        Arrays.setAll(max, index->0);

        if(isReverseOrder){
            //注意这个倒叙的build！！！
            for(int i=array.length-1;i>=0;i--){
                if(i-len+2>=array.length-1){
                    max[i-len+1]=array[i];
                }
                if(i-len+1>=0 && i-len+2<array.length){
                    max[i-len+1]=Math.max(max[i-len+2],array[i]);
                }
            }
        } else{
            for(int i=0;i<array.length;i++){
                if(i==0){
                    max[i]=array[i];
                } else{
                    max[i]=Math.max(max[i-1],array[i]);
                }
            }
        }
        return max;
    }

    private int[] getSumOfWindowN(int[] prefixSum,int n){
        int[] sumOfWindowN = new int[prefixSum.length-1];
        for(int i=0;i<sumOfWindowN.length;i++){
            if(i+1-n<0){
                sumOfWindowN[i]=0;
            } else{
                sumOfWindowN[i]=prefixSum[i+1]-prefixSum[i+1-n];
            }
        }
        return sumOfWindowN;

    }

    private int[] getPrefixSum(int[] A){
        int[] prefixSum = new int[A.length+1];
        prefixSum[0]=0;
        for(int i=0;i<A.length;i++){
            prefixSum[i+1]=prefixSum[i]+A[i];
        }

        return prefixSum;
    }
}
