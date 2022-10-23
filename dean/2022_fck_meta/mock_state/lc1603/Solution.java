package mock_state.lc1603;

public class Solution {
    private int b,m,s;
    public Solution(int big, int medium, int small) {
        b = big;
        m = medium;
        s = small;
    }

    public boolean addCar(int carType) {
        if(carType==1 && b>0){
            b--;
            return true;
        }
        if(carType==2 && m>0){
            m--;
            return true;
        }
        if(carType==3 && s>0){
            s--;
            return true;
        }
        return false;
    }
}
