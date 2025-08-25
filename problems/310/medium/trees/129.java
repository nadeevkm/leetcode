
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

    public int sumNumbers(TreeNode root) {
        return sumNodesRec(root, 0);
    }

    public int sumNodesRec(TreeNode root, int sum){
        int currSum  = 10*sum + root.val;

        if (root.left == null && root.right == null){
            return currSum;
        }

        int leftSum = root.left == null ? 0 : sumNodesRec(root.left, currSum);
        int rightSum = root.right == null ? 0 : sumNodesRec(root.right, currSum);
        return leftSum + rightSum;
    }

//    public int sumNumbers(TreeNode root) {
//        int[] sumWrap = new int[]{0};
//        sumNodesRec(root, 0, sumWrap);
//        return sumWrap[0];
//    }
//
//    public void sumNodesRec(TreeNode root, int number, int[] sum){
//        if (root == null){
//            return;
//        }
//        int curNum = 10*number + root.val;
//
//        if (root.left == null && root.right == null){
//            sum[0] = sum[0] + curNum;
//        }
//
//        sumNodesRec(root.left, curNum, sum);
//        sumNodesRec(root.right, curNum, sum);
//    }
}

class Check {
    public static void main(String[] args) {
        var node1 = new TreeNode(1);
        var node2 = new TreeNode(2);
        var node3 = new TreeNode(3);
        var node4 = new TreeNode(4);
        var node5 = new TreeNode(5);
        var node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        new Solution().sumNumbers(node1);
        node3 = node3;
    }
}


class SolutionLatest {
    public int sumNumbers(TreeNode root) {
        return sumRecur(root, 0);
    }

    public int sumRecur(TreeNode root, int base) {
        base = base * 10 + root.val;
        if (root.left == null && root.right == null) {
            return base;
        } else {
            int left = root.left == null ? 0 : sumRecur(root.left, base);
            int right = root.right == null ? 0 : sumRecur(root.right, base);
            return left + right;
        }
    }
}

class SolutionWithGlobalVar {
    int leafSum = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return leafSum;
    }

    public void dfs(TreeNode root, int base) {
        if (root == null) {
            return;
        }
        base = base * 10 + root.val;
        if (root.left == null && root.right == null) {
            leafSum += base;
        }
        dfs(root.left, base);
        dfs(root.right, base);
    }
}