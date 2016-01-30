/**

Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]

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
        
        String curr = "";
        ArrayList<String> result = new ArrayList<String>();
        
        if(root==null)
        {
            //result.add(curr); 
            return result;
        }

        return btpHelper(root, result, curr);
    }
    
    public ArrayList<String> btpHelper(TreeNode root, ArrayList<String> result, String curr)
    {

        if((root.left==null)&& (root.right==null))
        {
            curr = curr + Integer.toString(root.val);
            result.add(curr);
            return result;
        }
        else
        {
            curr = curr + Integer.toString(root.val);
            curr = curr  +"->";
        }
            
        if (root.left!= null)
            result = btpHelper(root.left, result,curr);
        if (root.right!= null)
            result = btpHelper(root.right, result,curr);
        
        return result;
        
    }
    
}