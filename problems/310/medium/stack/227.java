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
            var opLeft = operands.pollLast(); // !!! not just pop(), instead process from left to right -> example "1-1+1" might get 1 - (1 + 1) instead of 1 - 1 + 1
            var opRight = operands.pollLast();
            var res = ops.pollLast() == '-' ? opLeft - opRight : opLeft + opRight;
            operands.addLast(res);
        }
        return operands.pop();
    }
}


import java.util.Deque;
import java.util.LinkedList;

class SolutionSmooth {
    public int calculate(String s) {
        Deque<Integer> st = new LinkedList<>();
        int p = 0;
        char op = '+';
        int prevNum = 0;
        int res = 0;
        while (p < s.length()) {
            if (s.charAt(p) == ' ') {
                p++;
            } else if (Character.isDigit(s.charAt(p))) {
                var num = 0;
                while (p < s.length() && Character.isDigit(s.charAt(p))) {
                    num = num * 10 + (s.charAt(p) - '0');
                    p++;
                }
                switch (op) {
                    case '+':
                        res = res + prevNum;
                        prevNum = num;
                        break;
                    case '-':
                        res = res + prevNum;
                        prevNum = (-1)*num;
                        break;
                    case '/':
                        prevNum = prevNum / num;
                        break;
                    case '*':
                        prevNum = prevNum * num;
                        break;
                }
            } else { // +, -, /, *
                op = s.charAt(p);
                p++;
            }
        }
        res += prevNum;
        return res;
    }
}