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

/*
             1
1          1
        0/  \1
2       2    3
      0/\1  0/\1
3    4   5  6  null    (4 here is 100, 5 here is 101, 6 here is 110)

 */

class Solution {
    public int countNodes(TreeNode root) {
        int h = getHeight(root);
        int l = (int) Math.pow(2, h - 1);
        int r = (int) Math.pow(2, h) - 1;
        while (l < r) {
            int m = r - (r - l) / 2;
            if (getNode(root, m, h) != null) { // i.e. m-th node is present in the tree
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    public TreeNode getNode(TreeNode root, int nodeNum, int heigh) {
        for (int i = heigh - 2; i >= 0; i--) { // we want to start from 2nd digit and skip root node (it is always 1, we don't want to check it)
            int choice = nodeNum >>> i & 1;
            root = choice == 0 ? root.left : root.right;
        }
        return root;
    }

    public int getHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            root = root.left;
            height++;
        }
        return height;
    }
}

class Check {
    public static void main(String[] args) {
        var n1 = new TreeNode(1);
        var n2 = new TreeNode(2);
        var n3 = new TreeNode(3);
        var n4 = new TreeNode(4);
        var n5 = new TreeNode(5);
        var n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;

        int p = new Solution().countNodes(n1);
    }
}
