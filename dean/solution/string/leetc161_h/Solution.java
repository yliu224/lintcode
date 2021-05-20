package string.leetc161_h;

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if(Math.abs(s.length()-t.length())>1) return false;
        
        //注意这种预处理的方法,这样可以很方便的处理空字符串的问题
        s="#"+s+"#";
        t="#"+t+"#";
        
        boolean isChanged = false;
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=t.charAt(i)){
                if(isChanged){
                    return false;
                } else{
                    isChanged=true;
                    if(s.length()<t.length()){
                        s=s.substring(i);
                        t=t.substring(i+1);
                        i=-1;
                    } else if(s.length()==t.length()){
                        continue;
                    }else if(s.length()>t.length()){
                        s=s.substring(i+1);
                        t=t.substring(i);
                        i=-1;
                    }
                }
            }
        }
        
        return isChanged;
    }
}