package mock_state.leetc1570_e;

class SparseVector {
    private int[] vector;

    SparseVector(int[] nums) {
        this.vector = nums;
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int product = 0;
        for (int i = 0; i < this.vector.length; i++) {
            if (this.vector[i] != 0 && vec.vector[i] != 0) {
                product += this.vector[i] * vec.vector[i];
            }
        }

        return product;
    }
}
