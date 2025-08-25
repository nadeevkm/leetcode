import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
    public int widthOfBinaryTree(TreeNode root) {
        long res = 0;
        Queue<Object[]> q = new LinkedList<>();
        q.add(new Object[]{1L, root});
        while (!q.isEmpty()) {
            int levelSize = q.size();
            long levelMinInd = Long.MAX_VALUE;
            long levelMaxInd = Long.MIN_VALUE;
            while (levelSize-- > 0) {
                var indToNode = q.poll();
                long ind = (long) indToNode[0];
                TreeNode node = (TreeNode) indToNode[1];

                levelMinInd = Math.min(levelMinInd, ind);
                levelMaxInd = Math.max(levelMaxInd, ind);

                if (node.right != null) {
                    q.add(new Object[]{ind * 2, node.right});
                }
                if (node.left != null) {
                    q.add(new Object[]{ind * 2 - 1, node.left});
                }
            }
            res = Math.max(res, levelMaxInd - levelMinInd + 1);
        }
        return (int) res;
    }
}


class SolutionOptimized {
    public int widthOfBinaryTree(TreeNode root) {
        int res = 0;
        Deque<Object[]> q = new LinkedList<>();
        q.add(new Object[]{0, root}); // more traditional to start index from 0
        while (!q.isEmpty()) {
            int levelSize = q.size();

            int levelMinInd = (int) q.peekFirst()[0]; // can derive min and max of levels right away by their positions in Q
            int levelMaxInd = (int) q.peekLast()[0];
            res = Math.max(res, levelMaxInd - levelMinInd + 1);

            while (levelSize-- > 0) {
                var indToNode = q.poll();
                long ind = (int) indToNode[0] - levelMinInd; // to reduce overflow, make index relative (not absolute) to the smallest index of level
                TreeNode node = (TreeNode) indToNode[1];

                if (node.right != null) {
                    q.add(new Object[]{ind * 2 + 1, node.right});
                }
                if (node.left != null) {
                    q.add(new Object[]{ind * 2 + 2, node.left});
                }
            }
        }
        return res;
    }
}

class SolutionWithPair {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        var maxWidth = 0;
        while (!q.isEmpty()) {
            int levSize = q.size();
            int minNum = q.peek().second;
            int maxNum = q.peek().second;
            while (levSize-- > 0) {
                var pair = q.poll();
                var node = pair.first;
                var num = pair.second;

                minNum = Math.min(minNum, num);
                maxNum = Math.max(maxNum, num);

                if (node.left != null) {
                    q.add(new Pair(node.left, num * 2));
                }
                if (node.right != null) {
                    q.add(new Pair(node.right, num * 2 + 1));
                }
            }
            maxWidth = Math.max(maxWidth, maxNum - minNum + 1);
        }
        return maxWidth;
    }
}

class Pair{
    TreeNode first;
    int second;

    public Pair(TreeNode node, int num){
        first = node;
        second = num;
    }
}