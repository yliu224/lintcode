package mock_state.lc1352;

public class Solution {
    private long[] products;
    private int index, zero;

    public Solution() {
        this.products = new long[50005];
        this.index = 0;
        this.zero = -1;
    }

    public void add(int num) {
        if (index == 0 || products[index - 1] == 0) {
            products[index] = num;
        } else {
            products[index] = products[index - 1] * num;
        }
        if (num == 0) {
            zero = index;
        }
        index++;
    }

    public int getProduct(int k) {
        int top = index - 1;
        if (top - zero < k) {
            return 0;
        }
        return (int) (products[top] / getProductK(k));
    }

    private int getProductK(int k) {
        int top = index - 1;
        if (top - k < 0 || products[top - k] == 0) {
            return 1;
        }
        return (int) products[top - k];
    }
}
