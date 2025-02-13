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
    public int goodNodes(TreeNode root) {
        return goodNodes(root, Integer.MIN_VALUE); // !! my fault, not 0, cause vals doesn't start from 0
    }

    public int goodNodes(TreeNode root, int prevMax) {
        if (root == null) {
            return 0;
        }

        boolean isGood = (root.val >= prevMax);
        int max = Math.max(prevMax, root.val);
        int countLeft = goodNodes(root.left, max);
        int countRight = goodNodes(root.right, max);
        int total = countLeft + countRight + (isGood ? 1 : 0);

        return total;
    }
}
