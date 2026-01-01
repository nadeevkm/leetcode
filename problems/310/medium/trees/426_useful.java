class Node {
    int val;
    Node left;
    Node right;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    Node head = null;
    Node pred = null;

    public Node treeToDoublyList(Node root) {
        if (root == null){
            return null;
        }
        head = null;
        pred = null;

        inOrderDfs(root);
        pred.right = head;
        head.left = pred;
        return head;
    }

    public void inOrderDfs(Node root){
        if (root == null){
            return;
        }
        inOrderDfs(root.left);
        if (pred != null){
            pred.right = root;
        }
        root.left = pred;
        if (head == null){
            head = root;
        }
        pred = root;
        inOrderDfs(root.right);
    }
}