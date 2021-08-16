/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

Example 1:


Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [1]
Output: [1]
Example 4:

Input: root = [1,2]
Output: [1,2]
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
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
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serialize(root, "");
    }

    private String serialize(TreeNode root, String encoded) {
        if (root == null) {
            encoded += "null";
            return encoded;
        }

        String left = serialize(root.left, encoded);
        String right = serialize(root.right, encoded);

        return String.valueOf(root.val) + "," + left + "," + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(arr));
        return deserialize(queue);
    }

    private TreeNode deserialize(Queue<String> queue) {
        TreeNode root = null;
        if (!queue.isEmpty()) {
            String polledItem = queue.poll();

            if(polledItem.equals("null")) {
                return null;
            }

            root = new TreeNode(Integer.valueOf(polledItem));
            root.left = deserialize(queue);
            root.right = deserialize(queue);
        }
        return root;
    }
}
