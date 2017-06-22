/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //满足答案的return cases,当就只有一个node的时候，这个node不是null, p or q
        if(root == null || root == p || root == q){
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //如果root有left 和right，那就返回root
        if(left != null && right != null)
            return root;
        //如果只找到left就返回left
        if(left != null)
            return left;
        //如果只找到right就返回right
        if(right != null)
            return right;
            
        return null;
    }
}

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //if the tree is empty
        if (root == null) {
            return null;
        }
        //if both nodes are larger than the root, search the right branch
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else { //if the two nodes are on different side of the root node, it means the root node is the answer
            return root;
        }
    }
}