/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 */
import java.util.*;
public class BinaryTreeZigzagLevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Deque<TreeNode> dq = new LinkedList<>();
        List<List<Integer>> zigzag = new LinkedList<>();
        
        if (root == null)
            return zigzag;
        
        dq.add(root);
        int rtl = 0;
        
        while(true) {    
            int size = dq.size();
            
            if (size==0) {
                break;
            }
            List<Integer> level = new LinkedList<>();
            while (size > 0) {
                TreeNode node;
                size--;
                if (rtl==0) {
                    node = dq.pollLast(); 
                    // Insert right before left so that in next iteration, right can be picked first from RTL = 1 from front end. 
                    if (node.left != null)
                        dq.offerFirst(node.left);
                    if (node.right != null)
                        dq.offerFirst(node.right);
                    
                } else {
                    node = dq.pollFirst();
                    // Insert left at end, so that left can be picked first in next iteration while rtl = 0 from rear end. 
                    if (node.right != null)
                        dq.offerLast(node.right);
                    if (node.left != null)
                        dq.offerLast(node.left);
                    
                }
                level.add(node.val);
            }
            zigzag.add(level);
            rtl = 1 - rtl;
        }
        
        return zigzag;
    }
}
