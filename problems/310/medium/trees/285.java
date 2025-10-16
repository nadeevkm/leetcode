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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return dfs(root, p, null);
    }

    public TreeNode dfs(TreeNode root, TreeNode p, TreeNode upperBound){
        if (root.val == p.val){
            return root.right == null ? upperBound : nextInorder(root.right);
        } else if (p.val < root.val){
            return dfs(root.left, p, root);
        } else {
            return dfs(root.right, p, upperBound);
        }
    }

    public TreeNode nextInorder(TreeNode node){
        if (node.left == null){
            return node;
        }
        var res = nextInorder(node.left);
        if (res != null){
            return res;
        }
        return node;
    }
}


class SolutionIterative { // idea is that based on current val we not only can decide whhere to move before finding target,
    // but also we know that inorder successor will be bigger
    // so we can continue traversal after finding with the same criteria, saving previos possible succesor
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successorCandidate = null;
        while (root != null){
            if (p.val < root.val){
                successorCandidate = root;
                root = root.left;
            } else { // p.val >= root.val
                root = root.right;
            }
        }
        return successorCandidate;
    }
}

class SolutionRepetit {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode prev = null;
        // searching p, saving prev if going left
        // while(root != null && root.val != p.val) - root != null not required, cause p is guaranteed to be in the tree
        while(root.val != p.val){
            if(root.val < p.val){
                root = root.right;
            } else {
                prev = root;
                root = root.left;
            }
        }
        // if p has right subtree - successor is there, if not successor is prev
        if (root.right != null){
            root = root.right;
            while(root.left != null){
                root = root.left;
            }
            return root;
        }
        return prev;
    }
}
