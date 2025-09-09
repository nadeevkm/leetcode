class MinStack { // Nodes

    StackNode top;

    public MinStack() {
        top = null;
    }

    // 5, 2, 10, 4, 4, 7
    public void push(int val) {
        if (top == null){
            top = new StackNode(val, null, val);
        } else {
            var newNode = new StackNode(val, top, Math.min(val, top.currMin));
            top = newNode;
        }
    }

    public void pop() {
        if (top == null){
            return;
        }
        var candidate = top.prev;
        top = top.prev;
    }

    public int top() {
        if (top == null){
            return -1;
        }
        return top.val;
    }

    public int getMin() {
        if (top == null){
            return -1;
        }
        return top.currMin;
    }

    class StackNode{
        int val;
        StackNode prev;
        int currMin;

        public StackNode(int val, StackNode node, int min){
            this.val = val;
            prev = node;
            currMin = min;
        }
    }
}

class MinStackRepetit { // TwoStacks

    StackNode top;

    public MinStack() {
        top = null;
    }

    // 5, 2, 10, 4, 4, 7
    public void push(int val) {
        if (top == null){
            top = new StackNode(val, null, val);
        } else {
            var newNode = new StackNode(val, top, Math.min(val, top.currMin));
            top = newNode;
        }
    }

    public void pop() {
        if (top == null){
            return;
        }
        var candidate = top.prev;
        top = top.prev;
    }

    public int top() {
        if (top == null){
            return -1;
        }
        return top.val;
    }

    public int getMin() {
        if (top == null){
            return -1;
        }
        return top.currMin;
    }

    class StackNode{
        int val;
        StackNode prev;
        int currMin;

        public StackNode(int val, StackNode node, int min){
            this.val = val;
            prev = node;
            currMin = min;
        }
    }
}
