package two_pointer.lc278;

class VersionControl {
    boolean isBadVersion(int n){
        return  true;
    }
}

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        long l=1,r=n;
        while(l+1<r){
            long mid = (l+r)/2;
            if(isBadVersion((int)mid)){
                r=mid;
            } else {
                l=mid;
            }
        }
        if(isBadVersion((int)l)){
            return (int)l;
        }
        return (int)r;
    }
}
