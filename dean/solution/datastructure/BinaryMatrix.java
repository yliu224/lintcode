package datastructure;

import java.util.*;

// This is the BinaryMatrix's API interface.
// You should not implement it, or speculate about its implementation
public interface BinaryMatrix {
    public int get(int row, int col);

    public List<Integer> dimensions();
};
