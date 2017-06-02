public class Solution {
    //通过DFS记录最大路径，maxValue记录每个subtree的最大值。
    int maxValue;
    
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPath(root);
        return maxValue;
    }
    
    private int maxPath(TreeNode node) {
      if(root == null)
            return 0;
        int leftMax = Math.max(0, maxPath(root.left));
        int rightMax = Math.max(0, maxPath(root.right));
        //记录最大的sum
        maxValue = Math.max(maxValue, leftMax + rightMax + root.val);
        //左右两边选最大的路径
        return Math.max(leftMax,rightMax) + root.val;
    }
}