class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int[] res = new int[]{0};
        inorder(root, k, new int[]{1}, res);
        return res[0];
    }

    public void inorder(TreeNode root, int k, int[] count, int[] res) {
        if (root == null) {
            return;
        }

        inorder(root.left, k, count, res);

        if (count[0] == k){
            res[0] = root.val;
        }
        count[0] = count[0] + 1;

        inorder(root.right, k, count, res);
    }
}
