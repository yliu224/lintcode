package string.lc1431_h;

//String的操作要点
//subString(start,end), 一定是取start，不取end，左闭右开，[start,end)
public class Solution {
    public String pushDominoes(String dominoes) {
        if(dominoes.length()<=1) return dominoes;
        int start = 0;
        StringBuilder finalState = new StringBuilder();
        for(int i=1;i<dominoes.length();i++){
            if(dominoes.charAt(i)!='.' || i==dominoes.length()-1){
                finalState.append(pushDominoes(start,i,dominoes));
                start = i;
            }
        }
        //特殊case要思考
        if(finalState.charAt(dominoes.length()-2)=='R' && dominoes.charAt(dominoes.length()-1)=='.'){
            finalState.append('R');
        } else{
            finalState.append(dominoes.charAt(dominoes.length()-1));
        }
        return finalState.toString();
    }

    private String pushDominoes(int startIndex,int endIndex,String dominoes){
        StringBuilder expectedState = new StringBuilder();
        char startChar = dominoes.charAt(startIndex);
        char endChar = dominoes.charAt(endIndex);
        int len = endIndex-startIndex;
        if((startChar == '.' && endChar == 'L') || (startChar == 'L' && endChar == 'L')){
            for(int i=0;i<len+1;i++){
                expectedState.append('L');
            }
        }

        if(startChar == 'R' && endChar == 'L'){
            int diff = len-1;
            expectedState.append('R');
            for(int i=0;i<diff/2;i++){
                expectedState.append('R');
            }
            if((diff)%2==0){//R..L => RRLL
                for(int i=diff/2+1;i<len;i++){
                    expectedState.append('L');
                }
            } else{ //R...L => RR.LL
                expectedState.append('.');
                for(int i=diff/2+2;i<len;i++){
                    expectedState.append('L');
                }
            }
            expectedState.append('L');
        }

        if((startChar == 'R' && endChar == 'R') || (startChar == 'R' && endChar == '.')){
            for(int i=0;i<len+1;i++){
                expectedState.append('R');
            }
        }

        if(expectedState.length()==0){
            expectedState.append(dominoes.substring(startIndex, endIndex+1));
        }
        expectedState.deleteCharAt(len);
        return expectedState.toString();
    }
}
