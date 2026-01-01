/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int[] preorder;
    int[] inorder;
    int[] pointerWrapper;
    Map<Integer, Integer> inorderInd;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        pointerWrapper = new int[1];
        inorderInd = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            inorderInd.put(inorder[i], i);
        }
        return build(pointerWrapper, 0, inorder.length - 1);
    }

    private TreeNode build(int[] pointerWrapper, int li, int ri){
        int ind = pointerWrapper[0];
        if (ind >= preorder.length || li > ri){
            return null;
        }

        TreeNode node = new TreeNode(preorder[ind]);
        pointerWrapper[0]++;

        int inorderIndex = inorderInd.get(node.val);
        node.left = build(pointerWrapper, li, inorderIndex - 1);
        node.right = build(pointerWrapper, inorderIndex + 1, ri);

        return node;
    }
}