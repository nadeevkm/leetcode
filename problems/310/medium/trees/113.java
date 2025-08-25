import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root != null) {
            dfs(new ArrayList<>(), root, targetSum);
        }
        return res;
    }

    private void dfs(List<Integer> path, TreeNode root, int leftSum) {
        // if (leftSum - root.val < 0) {   /// !!! - important, cause it is possible to have negative numbers within the path
        //     return;
        // }
        leftSum = leftSum - root.val;
        path.add(root.val);
        if (leftSum == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(path));
        } else {
            if (root.left != null) {
                dfs(path, root.left, leftSum);
            }
            if (root.right != null) {
                dfs(path, root.right, leftSum);
            }
        }
        path.remove(path.size() - 1);
    }
}
