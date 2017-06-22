/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//Top solution
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        //First consider the base cases
        if(root == null)
            return false;
        // return when reaching leaf and sum==target
        if(root.left == null && root.right == null && sum-root.val==0)
        return true;
        
        return(hasPathSum(root.left, sum-root.val) || hasPathSum(root.right,sum - root.val));
        
    }
}
//My solution
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;
        return dfs(sum,0,root);
    }
    
    private boolean dfs(int targetSum, int subSum,TreeNode root){
        if(root == null)
            return false;
            
          subSum += root.val;
          
         if(root.left == null && root.right == null){
            if(subSum == targetSum)
                return true;
            else
                return false;
        }    
        
        boolean left = dfs(targetSum, subSum, root.left);
        boolean right = dfs(targetSum, subSum, root.right);
        return left||right;
    }
}