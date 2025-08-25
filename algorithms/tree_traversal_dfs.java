class Scratch {
    public static void main(String[] args) {
        
    }
}


class PreorderTraversal {
    public void dfs(Node root){
        if (root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        visit();
    }
}

class InorderTraversal {
    public void dfs(Node root){
        if (root == null) {
            return;
        }
        dfs(root.left);
        visit();
        dfs(root.right);
    }
}

class PostorderTraversal {
    public void dfs(Node root){
        if (root == null) {
            return;
        }
        visit();
        dfs(root.left);
        dfs(root.right);
    }
}