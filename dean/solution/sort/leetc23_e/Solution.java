package sort.leetc23_e;

import java.util.PriorityQueue;

import datastructure.ListNode;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);
        
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){
                pq.add(lists[i]);
            }
        }
        
        if(pq.isEmpty()) return null;
        
        ListNode root= pq.poll();
        ListNode preNode = root;
        if(root.next!=null){
            pq.add(root.next);
        }
        
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            preNode.next=node;
            preNode = node;
            if(node.next!=null){
                pq.add(node.next);
            }
        }
        return root;
    }
}
