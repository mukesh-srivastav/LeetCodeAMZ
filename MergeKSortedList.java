/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6

 */
import java.util.*;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        int N = lists.length;
        ListNode current = null;
        
        if (N <1)
            return current;
        
        PriorityQueue<ListNode> mh = new PriorityQueue<ListNode>(N, (n1, n2) -> (n1.val - n2.val));
          
        for (int i=0; i<N; i++) {
            if (lists[i] != null)
                mh.add(lists[i]); 
        }
        
        ListNode res = null;
          
        while(!mh.isEmpty()) {
            ListNode min = mh.poll();
            if (min.next!=null) {
                mh.add(min.next);
            }
              
            if (current==null) {
                current = new ListNode(min.val);
            } else {
                current.next = new ListNode(min.val);
                current = current.next;
            }
            
            if (res==null) {
                res = current;
            }
        }
          
        return res;
    }
}
