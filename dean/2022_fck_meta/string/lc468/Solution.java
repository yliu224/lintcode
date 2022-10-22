package string.lc468;

public class Solution {
    public String validIPAddress(String queryIP) {
        if(queryIP.indexOf(".")!=-1){
            return isValidIPV4(queryIP);
        } else if(queryIP.indexOf(":")!=-1){
            return isValidIPV6(queryIP);
        }
        return "Neither";
    }

    private String isValidIPV4(String ip){
        String[] numbers = ip.split("\\.");
        //多考虑一些极端情况
        if(numbers.length!=4 || ip.startsWith(".") || ip.endsWith(".")){
            return "Neither";
        }

        for(String n:numbers){
            if(!isValidV4Number(n)){
                return "Neither";
            }
        }
        return "IPv4";
    }

    private boolean isValidV4Number(String s){
        //多考虑一些极端情况
        if(s.length()==0 || (s.charAt(0)=='0'&&s.length()>1) || s.length()>3){
            return false;
        }
        try{
            int n = Integer.parseInt(s);
            return n>=0 && n<=255;
        } catch(Exception e){
            return false;
        }
    }

    private String isValidIPV6(String ip){
        String[] numbers = ip.split(":");
        //多考虑一些极端情况
        if(numbers.length>8 || ip.startsWith(":") || ip.endsWith(":")){
            return "Neither";
        }
        for(String n:numbers){
            if(!isValidV6Number(n)){
                return "Neither";
            }
        }
        return "IPv6";
    }

    private boolean isValidV6Number(String s){
        if(s.length()==0 || s.length()>4){
            return false;
        }
        for(char c:s.toCharArray()){
            if((c>='0' && c<='9') || (c>='a' && c<='f') || (c>='A' && c<='F')){
                continue;
            }
            return false;
        }
        return true;
    }
}
