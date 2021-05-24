package sort.leetc295_h;

import java.util.*;

public class MedianFinder {
    // Heap的练习使用
    private PriorityQueue<Integer> bigHeap;
    private PriorityQueue<Integer> smallHeap;

    public MedianFinder() {
        this.bigHeap = new PriorityQueue<>((a, b) -> b - a);
        this.smallHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        smallHeap.add(num);

        if (!smallHeap.isEmpty() && !bigHeap.isEmpty() && smallHeap.peek() < bigHeap.peek()) {
            int small = smallHeap.poll();
            int big = bigHeap.poll();
            bigHeap.add(small);
            smallHeap.add(big);
        }

        if (smallHeap.size() - bigHeap.size() >= 2) {
            int top = smallHeap.poll();
            bigHeap.add(top);
        }

    }

    public double findMedian() {
        if (smallHeap.size() - bigHeap.size() == 1) {
            return smallHeap.peek();
        } else {
            return (smallHeap.peek() + bigHeap.peek()) / 2.0;
        }
    }
}
