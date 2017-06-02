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
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> result = new ArrayList<>();
        dfs(root, "", result);
        return result;
    }
    
    private void dfs(TreeNode root, String path, ArrayList<String> res){
        if(root == null)
            return;
        if(root.left == null && root.right == null){
            res.add(path+root.val);
        }
        dfs(root.left, path+ root.val+"->",res);
        dfs(root.right,path+ root.val+"->",res);
    }
}