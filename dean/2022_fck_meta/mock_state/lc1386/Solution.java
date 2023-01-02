package mock_state.lc1386;

public class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int[] reserved = new int[n+1];

        for (int i = 0; i < reservedSeats.length; i++) {
            int r = reservedSeats[i][0];
            int s = reservedSeats[i][1];

            int seat = 1<<(10-s);
            //System.out.println(r+":"+s+"->"+Integer.toBinaryString(seat));
            reserved[r]|=seat;
        }

        int count = 0;
        for (int r = 1; r <= n; r++) {
            count += getCount(reserved[r]);
        }
        return count;
    }

    int getCount(int reserved) {
        if (reserved == 0) {
            return 2;
        }
        boolean left = false, midLeft = false, midRight = false, right = false;
        char[] bits = String.format("%10s", Integer.toBinaryString(reserved)).replaceAll(" ", "0").toCharArray();
        for (int i=0;i<bits.length;i++ ) {
            if(bits[i]=='0'){
                continue;
            }
            int r=i+1;
            if (r >= 2 && r <= 3) {
                left = true;
            }
            if (r >= 4 && r <= 5) {
                midLeft = true;
            }
            if (r >= 6 && r <= 7) {
                midRight = true;
            }
            if (r >= 8 && r <= 9) {
                right = true;
            }
        }
        if ((left || midLeft) && !midRight && !right) {
            return 1;
        }
        if ((right || midRight) && !midLeft && !left) {
            return 1;
        }
        if (!midLeft && !midRight && (right || left)) {
            return 1;
        }
        if (!midLeft && !midRight && !left && !right) {
            return 2;
        }
        return 0;
    }
}
