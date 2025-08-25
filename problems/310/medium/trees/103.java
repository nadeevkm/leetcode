import java.util.*;

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) { // calculate position in level view array, from start or from beginning
        Deque<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> view = new ArrayList<>();
        int level = 0;
        if (root != null) {
            queue.add(root);
            level++;
        }
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelView = Arrays.asList(new Integer[levelSize]);

            for (int i = 0; i < levelSize; i++) {
                var node = queue.poll();
                if (level % 2 == 1) {
                    levelView.set(i, node.val);
                } else {
                    levelView.set(levelSize - i - 1, node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            view.add(levelView);
            level++;
        }
        return view;
    }

    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) { // separate variable for traverse direction
        Deque<TreeNode> stackEven = new LinkedList<>();
        Deque<TreeNode> stackOdd = new LinkedList<>();
        List<List<Integer>> view = new ArrayList<>();
        if (root != null) {
            stackOdd.add(root);
        }
        boolean zig = true;
        var curStack = stackOdd;
        var nextStack = stackEven;
        while (!curStack.isEmpty()) {
            List<Integer> levelView = new ArrayList<>();
            while (!curStack.isEmpty()) {
                var node = curStack.pop();
                levelView.add(node.val);
                if (zig) {
                    if (node.left != null) {
                        nextStack.push(node.left);
                    }
                    if (node.right != null) {
                        nextStack.push(node.right);
                    }
                } else { // zag
                    if (node.right != null) {
                        nextStack.push(node.right);
                    }
                    if (node.left != null) {
                        nextStack.push(node.left);
                    }
                }
            }
            view.add(levelView);
            zig = !zig; // zig->zag, zag-zig
            curStack = (zig) ? stackOdd : stackEven;
            nextStack = (zig) ? stackEven : stackOdd;
        }
        return view;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) { // two stacks, one for odd and one for even level
        Deque<TreeNode> stackOdd = new LinkedList<>();
        Deque<TreeNode> stackEven = new LinkedList<>();
        List<List<Integer>> view = new ArrayList<>();
        if (root != null) {
            stackOdd.add(root);
        }
        while (stackEven.size() + stackOdd.size() > 0) {
            List<Integer> levelView = new ArrayList<>();
            while (!stackOdd.isEmpty()) {
                var node = stackOdd.pop();
                levelView.add(node.val);
                if (node.left != null) {
                    stackEven.push(node.left);
                }
                if (node.right != null) {
                    stackEven.push(node.right);
                }
            }
            view.add(levelView);

            if (stackEven.isEmpty()) {
                continue;
            }

            levelView = new ArrayList<>();
            while (!stackEven.isEmpty()) {
                var node = stackEven.pop();
                levelView.add(node.val);
                if (node.right != null) {
                    stackOdd.push(node.right);
                }
                if (node.left != null) {
                    stackOdd.push(node.left);
                }
            }
            view.add(levelView);
        }
        return view;
    }


}

class SolutionLatest {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        var res = new LinkedList<List<Integer>>();

        Deque<TreeNode> currLev = new LinkedList<>();
        Deque<TreeNode> nextLev = new LinkedList<>();
        if (root != null) {
            currLev.add(root);
        }

        boolean reverse = false;
        while (!currLev.isEmpty() || !nextLev.isEmpty()) {

            var level = new LinkedList<Integer>();
            while (!currLev.isEmpty()) {
                if (!reverse) {
                    var current = currLev.pollFirst();
                    level.add(current.val);
                    if (current.left != null) {
                        nextLev.add(current.left);
                    }
                    if (current.right != null) {
                        nextLev.add(current.right);
                    }
                } else { // reverse
                    var current = currLev.pollLast();
                    level.add(current.val);
                    if (current.right != null) {
                        nextLev.addFirst(current.right);
                    }
                    if (current.left != null) {
                        nextLev.addFirst(current.left);
                    }
                }
            }

            res.add(level);
            var tmp = currLev;
            currLev = nextLev;
            nextLev = tmp;
            reverse = !reverse;
        }
        return res;
    }
}