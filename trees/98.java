import java.util.Deque;
import java.util.LinkedList;

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
    public boolean isValidBST(TreeNode root) {
        return inorderIterative(root);
    }

    public boolean inorderIterative(TreeNode root) {

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            var node = stack.pop();
            if (prev != null && prev.val >= node.val) {
                return false;
            }
            prev = node;
            root = node.right;
        }

        return true;
    }

//    public boolean isValidBST(TreeNode root) {
//        return inorder(root, new Integer[]{ null });
//    }

    public boolean inorder(TreeNode root, Integer[] prev) {
        if (root == null) {
            return true;
        }

        var left = inorder(root.left, prev);

        if (left == false || (prev[0] != null && prev[0] >= root.val)) {
            return false;
        }
        prev[0] = root.val;

        var right = inorder(root.right, prev);

        return right;
    }
}
