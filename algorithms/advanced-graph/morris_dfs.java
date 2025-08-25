import java.util.LinkedList;
import java.util.List;

// https://www.youtube.com/watch?v=wGXB9OWhPTg
// see leetcode 94 https://leetcode.com/problems/binary-tree-inorder-traversal/editorial/#approach-3-morris-traversal

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

class MorrisTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        TreeNode current = root;
        while (current != null){
            if (current.left != null){
                TreeNode tmp = current.left;
                while (tmp.right != null && tmp.right != current){
                    tmp = tmp.right;
                }
                if (tmp.right != current){
                    tmp.right = current;
                    current = current.left;
                } else { // tmp.right == current, means we already visited left subtree, can visit 'current' and revert additional pointer
                    tmp.right = null;
                    res.add(current.val);
                    current = current.right;
                }
            } else {
                res.add(current.val);
                current = current.right;
            }
        }
        return res;
    }
}