import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (var t : tokens){
            if (t.equals("+")){
                var op1 = stack.pop();
                var op2 = stack.pop();
                var res = op2 + op1;
                stack.push(res);
            } else if (t.equals("-")) {
                var op1 = stack.pop();
                var op2 = stack.pop();
                var res = op2 - op1;
                stack.push(res);
            } else if (t.equals("/")) {
                var op1 = stack.pop();
                var op2 = stack.pop();
                var res = op2 / op1;
                stack.push(res);
            } else if (t.equals("*")) {
                var op1 = stack.pop();
                var op2 = stack.pop();
                var res = op2 * op1;
                stack.push(res);
            } else {
                stack.push(Integer.valueOf(t));
            }
        }
        return stack.pop();
    }
}

class SolutionAlt {
    public int evalRPN(String[] tokens) {
        Deque<Integer> st = new LinkedList<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*")) {
                int right = st.pop();
                int left = st.pop();
                int res = applyOp(left, right, token);
                st.push(res);
            } else {
                st.push(Integer.valueOf(token));
            }
        }
        return st.pop();
    }

    private int applyOp(int left, int right, String op){
        int res = 0;
        if (op.equals("-")){
            res = left - right;
        } else if (op.equals("+")){
            res = left + right;
        } else if (op.equals("*")){
            res = left * right;
        } else if (op.equals("/")){
            res = left / right;
        }
        return res;
    }
}