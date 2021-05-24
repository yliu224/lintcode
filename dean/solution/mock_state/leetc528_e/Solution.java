package mock_state.leetc528_e;

import java.util.Random;

public class Solution {
    private double[] probability;
    public Solution(int[] w) {
        this.probability = new double[w.length+1];
        int sum = 0;
        for(int i=0;i<w.length;i++){
            sum+=w[i];
        }
        probability[0]=0;
        for(int i=1;i<w.length;i++){
            probability[i]=probability[i-1]+w[i-1]/(double)sum;
        }
    }
    
    public int pickIndex() {
        double pick =  new Random().nextDouble();
        if(pick==0) return 0;
        if(pick==1) return probability.length-2;
        for(int i=1;i<probability.length;i++){
            if(probability[i-1]<pick && pick<=probability[i]){
                return i-1;
            }
        }

        return -1;
    }
}
