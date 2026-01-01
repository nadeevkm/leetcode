/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// three possible cases
// p <= r <= q  - LCA found
// p < q < r
// r < p < q
// exit root.val >= p.val && root.val <= q.val

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val){
            return lowestCommonAncestor(root, q, p);
        }
        while (root != null && !(root.val >= p.val && root.val <= q.val)){
            if (root.val > q.val){
                root = root.left;
            } else { // root.val < p.val
                root = root.right;
            }
        }
        return root;
    }
}