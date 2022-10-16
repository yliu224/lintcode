package divide_concur.lc148;

import datastructure.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        List<ListNode> array = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            array.add(cur);
            cur = cur.next;
        }
        List<ListNode> sortedList = mergeSort(array, 0, array.size() - 1);
        for (int i = 0; i < sortedList.size(); i++) {
            if (i + 1 < sortedList.size()) {
                sortedList.get(i).next = sortedList.get(i + 1);
            } else {
                sortedList.get(i).next = null;
            }
        }
        return sortedList.get(0);
    }

    private List<ListNode> mergeSort(List<ListNode> array, int l, int r) {
        if (l == r) {
            return new ArrayList<>(Arrays.asList(array.get(l)));
        }
        int mid = (l + r) / 2;
        List<ListNode> left = mergeSort(array, l, mid);
        List<ListNode> right = mergeSort(array, mid + 1, r);

        List<ListNode> sortedList = new ArrayList<>();
        int li = 0, ri = 0;
        while (li < left.size() && ri < right.size()) {
            if (left.get(li).val < right.get(ri).val) {
                sortedList.add(left.get(li++));
            } else {
                sortedList.add(right.get(ri++));
            }
        }
        while (li < left.size()) {
            sortedList.add(left.get(li++));
        }
        while (ri < right.size()) {
            sortedList.add(right.get(ri++));
        }

        return sortedList;
    }
}
