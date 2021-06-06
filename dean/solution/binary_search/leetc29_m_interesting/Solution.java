package binary_search.leetc29_m_interesting;

public class Solution {
    //注意这种一定要用long来计算
    public int divide(int dividend, int divisor) {
        long ldividend = dividend;
        long ldivisor = divisor;
        if(ldividend==ldivisor){
            return 1;
        }
        if(-ldividend==ldivisor){
            return -1;
        }
        if(ldividend==0 || Math.abs(ldividend)<Math.abs(ldivisor)){
            return 0;
        }
        
        boolean sign = (ldividend<0 && ldivisor<0) || (ldividend>0 && ldivisor>0);
        
        long result = 0;
        long sum = 0;
        while(sum<Math.abs(ldividend)&&sum+Math.abs(divisor)<Math.abs(ldividend)){//这儿判断条件
            long tmpResult = 1;
            long lastResult = 0;
            long lastAdded=0;
            long add = Math.abs(ldivisor);
            while(sum<Math.abs(ldividend)){//这儿判断条件
                lastResult = tmpResult;
                lastAdded = add;
                
                result+=tmpResult;
                sum+=add;
                
                
                add+=add;
                tmpResult+=tmpResult;
            }  
            sum=sum-lastAdded;
            result = result-lastResult;
        }

        if(sum+Math.abs(divisor)==Math.abs(ldividend)) result++;
        if(result==2147483648L && sign) result--;
        return sign?(int)(result):-(int)(result);
    }
}
