class BSTIterator {

	Deque<TreeNode> stack = new LinkedList();
	TreeNode cur = null;

    public BSTIterator(TreeNode root) {
		cur = root;
        while( cur != null){
			stack.push(cur);
			cur = cur.left;
		}
    }
    
    public int next() {
		while (cur != null){
			stack.push(cur);
			cur = cur.left;
		}
		TreeNode node = stack.pop();
		cur = node.right;
        
		return node.val;
    }
    
    public boolean hasNext() {
        return (cur != null || !stack.isEmpty());
    }
}

class BSTIteratorWithNextPointer {

    Deque<TreeNode> nodeStack;
    TreeNode next;

    public BSTIterator(TreeNode root) {
        nodeStack = new LinkedList<TreeNode>();
        next = root;
        while (next.left != null){
            nodeStack.push(next);
            next = next.left;
        }
    }
    
    public int next() {
        int res = next.val;
        if (next.right != null){
            next = next.right;
            while (next.left != null){
                nodeStack.push(next);
                next = next.left;
            }
        } else {
            next = nodeStack.isEmpty() ? null : nodeStack.pop();
        }
        return res;
    }
    
    public boolean hasNext() {
        return next != null;
    }
}