
/***
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].

***/

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
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();    
        int height = 0;
        int max = 0;
        if(root==null)
            return result;
        result.add(max);
        result.add(root.val);
        result = helper(root, height, max, result);
        result.remove(0);
        
        return result;
    }
    
    public List<Integer> helper(TreeNode root, int height, int max, List<Integer> result)
    {
        if(root==null)
            return result;
        if(height>result.get(0))
        {
            max = height;
            result.set(0,max);
            result.add(root.val);
        }
        result = helper(root.right, height + 1, max,result);
        result = helper(root.left, height + 1, max,result);
        
        return result;
    }
}