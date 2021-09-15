/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbers {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curr1 = l1, curr2 = l2, res = new ListNode(-1), resHead = res;
        int carry = 0;
        while (curr1!=null && curr2!=null) {
            int val = curr1.val + curr2.val + carry;
            
            if (val > 9) {
                carry = val/10;
                val = val%10;
            } else {
                carry = 0;
            }
            
            res.next = new ListNode(val);
            res = res.next;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        
        while (curr1 != null) {
            int val = curr1.val + carry;
            if (val > 9) {
                carry = val/10;
                val = val%10;
            } else {
                carry = 0;
            }
            
            res.next = new ListNode(val);
            res = res.next;
            curr1 = curr1.next;
        }
        
        while (curr2 != null) {
            int val = curr2.val + carry;
            if (val > 9) {
                carry = val/10;
                val = val%10;
            } else {
                carry = 0;
            }
            
            res.next = new ListNode(val);
            res = res.next;
            curr2 = curr2.next;
        }
        
        if (carry > 0) {
            res.next = new ListNode(carry);
            res = res.next; 
        }
        
        return resHead.next;
    }
}
