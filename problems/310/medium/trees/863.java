/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    Map<Integer, TreeNode> parents = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (root == null){
            return new LinkedList<>();
        }
        dfs(root);

        Queue<TreeNode> q = new LinkedList<>();
        Set<Integer> reached = new HashSet<>();
        q.add(target);
        reached.add(target.val);
        int layer = 0;
        while(!q.isEmpty()){
            if (layer == k){
                List<Integer> res = new LinkedList<>();
                for (var node : q){
                    res.add(node.val);
                }
                return res;
            }
            int size = q.size();
            while (size-- > 0){
                TreeNode curr = q.poll();
                if (curr.left != null && !reached.contains(curr.left.val)){
                    q.add(curr.left);
                    reached.add(curr.left.val);
                }
                if (curr.right != null && !reached.contains(curr.right.val)){
                    q.add(curr.right);
                    reached.add(curr.right.val);
                }
                var parent = parents.get(curr.val);
                if (parent != null && !reached.contains(parent.val)){
                    q.add(parent);
                    reached.add(parent.val);
                }
            }
            layer++;
        }
        return new LinkedList<>();
    }

    private void dfs(TreeNode root){
        if(root.left != null){
            parents.put(root.left.val, root);
            dfs(root.left);
        }
        if(root.right != null){
            parents.put(root.right.val, root);
            dfs(root.right);
        }
    }
}



class SolutionDfs {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, Object[]> pathToTarget = new HashMap<>();
        dfs(root, target, pathToTarget);
        var pathSize = pathToTarget.size();
        var res = new LinkedList<Integer>();
        for (var nodeToDist : pathToTarget.values()) {
            var node = (TreeNode) nodeToDist[0];
            var pathInd = (int) nodeToDist[1];
            dfs(node, pathSize - pathInd - 1, k, pathToTarget, res);
        }
        return res;
    }

    private boolean dfs(TreeNode root, TreeNode target, Map<Integer, Object[]> pathToTarget) {
        if (root == null){
            return false;
        }
        pathToTarget.put(root.val, new Object[]{root, pathToTarget.size()});
        if (root.val == target.val ||
                dfs(root.left, target, pathToTarget) ||
                dfs(root.right, target, pathToTarget)){
            return true;
        }
        pathToTarget.remove(root.val);
        return false;
    }

    private void dfs(TreeNode root, Integer distance, int k, Map<Integer, Object[]> pathToTarget, LinkedList<Integer> res) {
        if (distance > k) {
            return;
        }
        if (distance == k) {
            res.add(root.val);
            return;
        }
        if (root.left != null && !pathToTarget.containsKey(root.left.val)) {
            dfs(root.left, distance + 1, k, pathToTarget, res);
        }
        if (root.right != null && !pathToTarget.containsKey(root.right.val)) {
            dfs(root.right, distance + 1, k, pathToTarget, res);
        }
    }
}