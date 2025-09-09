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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, inorder.length - 1, new int[] { inorder.length - 1 });
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int l, int r, int[] postorderPointWrapper) {
        if (l > r || postorderPointWrapper[0] < 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorderPointWrapper[0]]);
        postorderPointWrapper[0]--;
        int pivot = l;
        while (inorder[pivot] != root.val) {
            pivot++;
        }  // !!! - in order not to do it every time, we can preprocess inorder array to the map of every value and according index (S = O(1) => O(n), T = O(n^2) ? => O(n))
        root.right = buildTree(inorder, postorder, pivot + 1, r, postorderPointWrapper);
        root.left = buildTree(inorder, postorder, l, pivot - 1, postorderPointWrapper);
        return root;
    }
}


class SolutionMap{
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return build(postorder, new int[] { postorder.length - 1 }, inorder, 0, inorder.length - 1, indexMap);
    }

    public TreeNode build(int[] postorder, int[] current, int[] inorder, int begin, int end,
            Map<Integer, Integer> indexMap) {

        if (begin > end || current[0] < 0) {
            return null;
        }

        TreeNode node = new TreeNode(postorder[current[0]]);

        int nextIndex = indexMap.get(node.val);

        current[0] = current[0] - 1;

        node.right = build(postorder, current, inorder, nextIndex + 1, end, indexMap);
        node.left = build(postorder, current, inorder, begin, nextIndex - 1, indexMap);
        return node;
    }
}
