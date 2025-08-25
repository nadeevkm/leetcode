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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return null;
        }

        TreeNode leftFound = lowestCommonAncestor(root.left, p, q);
        TreeNode rightFound = lowestCommonAncestor(root.right, p, q);

        if (leftFound != null && rightFound != null){
            return root;
        }
        boolean found = root.val == p.val || root.val == q.val;
        if (found){ // && (leftFound != null || rightFound != null)){
            return root;
        }

        return leftFound != null ? leftFound : rightFound;
    }

//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        return lcaRec(root, p, q, new FoundContext());
//    }

    public TreeNode lcaRec(TreeNode root, TreeNode p, TreeNode q, FoundContext fc){
        if (root == null){
            return null;
        }
        var leftCont = new FoundContext();
        TreeNode leftLca = lcaRec(root, p, q, leftCont);
        if (leftLca != null){
            return leftLca;
        }
        var rightCont = new FoundContext();
        TreeNode rightLca = lcaRec(root, p, q, rightCont);
        if (rightLca != null){
            return rightLca;
        }
        if (leftCont.numIsFound && rightCont.numIsFound){
            return root;
        }
        var isNumber = root.val == p.val || root.val == q.val;
        if (leftCont.numIsFound && isNumber){
            return root;
        }
        if (rightCont.numIsFound && isNumber){
            return root;
        }
        fc.numIsFound = leftCont.numIsFound || rightCont.numIsFound || isNumber;
        return  null;
    }

    class FoundContext{
        boolean numIsFound = false;
    }
}


class SolutionLatest {

    TreeNode target = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return target;
    }

    public int dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return 0;
        }

        int leftRes = dfs(root.left, p, q);
        if (leftRes == 2) {
            return 2;
        }

        int rightRes = dfs(root.right, p, q);
        if (rightRes == 2) {
            return 2;
        }

        int rootRes = (root.val == p.val || root.val == q.val) ? 1 : 0;
        if (rootRes + leftRes + rightRes == 2) {
            target = root;
            return 2;
        }

        return rootRes + leftRes + rightRes;
    }
}