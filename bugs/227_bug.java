class Solution {
    public int calculate(String s) {
        Deque<Integer> operands = new LinkedList<>();
        Deque<Character> ops = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
                if (i + 1 >= s.length() || !Character.isDigit(s.charAt(i + 1))) {
                    operands.push(num);
                    num = 0;
                    if (!ops.isEmpty() && (ops.peek() == '*' || ops.peek() == '/')) {
                        var opRight = operands.pop();
                        var opLeft = operands.pop();
                        operands.push(ops.pop() == '*' ? opLeft * opRight : opLeft / opRight);
                    }
                }
            } else {
                ops.push(ch);
            }
        }
        while (operands.size() > 1) {
            var opRight = operands.pop();
            var opLeft = operands.pop();
            var res = ops.pop() == '-' ? opLeft - opRight : opLeft + opRight;
            operands.push(res);
        }
        return operands.pop();
    }
}