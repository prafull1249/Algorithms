/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
public:
    TreeNode *sortedListToBST(ListNode *head) {
        int listLen = 0;
        ListNode *cur = head;
        while(cur) {
            listLen++;
            cur = cur->next;
        }
        return sortedListToBST(head, 0, listLen-1);
    }
    
    TreeNode *sortedListToBST(ListNode *&head, int start, int end) {
        if(start>end) return NULL;
        int mid = start + (end-start)/2;
        TreeNode *leftChild = sortedListToBST(head, start, mid-1);
        TreeNode *root = new TreeNode(head->val);
        head = head->next;
        TreeNode *rightChild = sortedListToBST(head, mid+1, end);
        root->left = leftChild;
        root->right = rightChild;
        return root;
    }
}