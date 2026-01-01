/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        reconnect(root, null, false);
        return root;
    }

    private void reconnect(Node root, Node parent, boolean left) {
        if (root == null) {
            return;
        }

        // visit
        if (left && parent.right != null){
            root.next = parent.right;
        } else {
            root.next = findNext(parent);
        }

        reconnect(root.right, root, false);
        reconnect(root.left, root, true);
    }

    private Node findNext(Node parent){
        if (parent == null){
            return null;
        }
        parent = parent.next;
        while (parent != null){
            if (parent.left != null){
                return parent.left;
            }
            if (parent.right != null){
                return parent.right;
            }
            parent = parent.next;
        }
        return null;
    }
}