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
    public int pathSum(TreeNode root, int targetSum) {
        var sums = new HashMap<Long, Integer>();
        sums.put(0L, 1);
        return dfs(root, targetSum, 0L, sums);
    }

    private int dfs(TreeNode root, int targetSum, Long total, Map<Long, Integer> prefSums){
        if (root == null){
            return 0;
        }

        total += root.val;
        int res = 0;
        res += prefSums.getOrDefault(total - targetSum, 0);
        prefSums.put(total, prefSums.getOrDefault(total, 0) + 1);

        res += dfs(root.left, targetSum, total, prefSums);
        res += dfs(root.right, targetSum, total, prefSums);

        prefSums.put(total, prefSums.get(total) - 1);

        return res;
    }
}
