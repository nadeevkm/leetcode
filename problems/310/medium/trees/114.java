class Solution {
    public void flatten(TreeNode root) {
        TreeNode head = null;
        TreeNode node = root;

        while (node != null){
            TreeNode nextNode = node.left;

            node.left = head;
            head = node;

            if (nextNode == null) {
                nextNode = head;
                while (nextNode != null && nextNode.right == null){
                    nextNode = nextNode.left;
                }
                if (nextNode != null){
                    var tmp = nextNode.right;
                    nextNode.right = null;
                    nextNode = tmp;
                }
            }

            node = nextNode;
        }

        //reverse head
        TreeNode prev = null;
        node = head;
        while(node != null){
            var nextNode = node.left;
            node.left = null;
            node.right = prev;
            prev = node;
            node = nextNode;
        }
    }
}

class SolutionRepetetion {
    public void flatten(TreeNode root) {
        flattenPaired(root);
    }

    private TreeNode[] flattenPaired(TreeNode root) {
        if (root == null) {
            return new TreeNode[] { null, null };
        }

        var leftListFirstLast = flattenPaired(root.left);
        var rightListFirstLast = flattenPaired(root.right);
        if (leftListFirstLast[1] != null) {
            leftListFirstLast[1].right = rightListFirstLast[0];
        }
        root.left = null;
        root.right = leftListFirstLast[0] == null
                ? rightListFirstLast[0]
                : leftListFirstLast[0];

        TreeNode last = rightListFirstLast[1] == null
                ? leftListFirstLast[1]
                : rightListFirstLast[1];
        return new TreeNode[] { root, last == null ? root : last };
    }
}

class SolutionRepetetion2 {
    public void flatten(TreeNode root) {
        flattenWithTail(root);
    }

    private TreeNode flattenWithTail(TreeNode root) {
        if (root == null) {
            return null;
        }

        var leftTail = flattenWithTail(root.left);
        var rightTail = flattenWithTail(root.right);

        if (leftTail != null) {
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        TreeNode tail = rightTail != null
                ? rightTail
                : (leftTail != null ? leftTail : root);

        return tail;
    }
}

class SolutionConcise {
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        flatten(root.left);
        flatten(root.right);

        TreeNode tmp = root.right;
        if (root.left != null){
            TreeNode pointer = root.left;
            while (pointer.right != null){
                pointer = pointer.right;
            }
            root.right = root.left;
            root.left = null;
            pointer.right = tmp;
        }
    }
}