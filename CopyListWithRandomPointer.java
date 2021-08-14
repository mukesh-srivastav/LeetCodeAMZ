/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

 

Example 1:


Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 */
import java.util.*; 
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyListWithRandomPointer {
    
    // Time: O(n), Space: O(1)
    public Node copyRandomList(Node head) {
        if(head == null)
             return head;
        
        Node node =new Node(head.val);
        Node temp =node;
        Node t    =head;
        
        //inserting duplicate nodes
        while(head != null)
        {
            Node k=head.next;
            head.next=node;
            node.next=k;
            head=head.next.next;
            
            if(head != null)
               node=new Node(head.val);
        }
        
        head=t;

        
        //assigning proper random pointers
        while(head != null)
        {
      
            if(head.next != null && head.random != null)
            {
                 head.next.random = head.random.next;
            }
            
            head=head.next.next;
        }
        
        head=t;
        
        Node res = new Node(0);
        t = res;
        
        //restoring lists
        while(head != null)
        {
            res.next = head.next;
            res = res.next;
            head.next = head.next.next;
            head = head.next;
        }
        

        
        return t.next;   
    } 
    
    //Time: O(n) Space: O(1)
    public Node copyRandomListWithOnSpace(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node runner = head;
        
        while (runner != null) {
            map.put(runner, new Node(runner.val));
            runner = runner.next;
        }
        
        runner = head;
        
        while (runner != null) {
            Node curr = map.get(runner);
            curr.next = map.get(runner.next);
            curr.random = map.get(runner.random);
            runner = runner.next;
        }
        
        return map.get(head);
    }
}
