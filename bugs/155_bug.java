class MinStack {

    Deque<Integer> stack;
    Deque<Integer> mins;

    public MinStack() {
        stack = new LinkedList<Integer>();
        mins = new LinkedList<Integer>();
    }

    public void push(int val) {
        stack.push(val);
        if (val < mins.peek()){
            mins.push(val);
        } else {
            mins.push(mins.peek());
        }
    }


    public void pop() {
        mins.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return mins.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */