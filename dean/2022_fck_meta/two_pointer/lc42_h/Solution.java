package two_pointer.lc42_h;

public class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int lmax = height[0], rmax = height[height.length - 1];
        int rain = 0;

        while (l < r) {
            if (lmax > rmax) {
                rain += rmax - height[r];
                r--;
                rmax = Math.max(rmax, height[r]);
            } else {
                rain += lmax - height[l];
                l++;
                lmax = Math.max(lmax, height[l]);
            }
        }
        return rain;
    }
}
