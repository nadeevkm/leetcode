class Solution {
    int longest = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfsLongest(root);
        return longest;
    }

    public int dfsLongest(TreeNode root){
        if(root == null){
            return 0;
        }

        int deepestLeft = dfsLongest(root.left);
        int deepestRight = dfsLongest(root.right);

        longest = Math.max(longest, deepestLeft + deepestRight);

        return 1 + Math.max(deepestLeft, deepestRight);
    }
}
