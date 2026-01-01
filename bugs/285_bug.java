class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode prev = null;
        while (root.val != p.val) {
            if (root.val < p.val) {
                root = root.right;
            } else {
                prev = root;
                root = root.left;
            }
        }
        return root.right != null ? root.right : prev;
    }
}