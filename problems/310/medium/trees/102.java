import java.util.ArrayList;
import java.util.Deque;
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> view = new ArrayList<>();
        if (root != null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> levelView = new ArrayList<>();
            for (int i = 1; i <= levelSize; i++){
                var node = queue.poll();

                levelView.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            view.add(levelView);
        }
        return view;
    }
}

class SolutionLatest {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null){
            return Collections.emptyList();
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> res = new LinkedList<>();
        while (!q.isEmpty()) {
            int levSize = q.size();
            var level = new LinkedList<Integer>();
            while (levSize-- > 0) {
                var node = q.poll();
                level.add(node.val);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }
}