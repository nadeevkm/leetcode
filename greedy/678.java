import java.util.Deque;
import java.util.LinkedList;

class Solution { // Greedy, always try to balance number of open vs closed
    public boolean checkValidString(String s) {
        int open = 0;
        int ast = 0;
        int astAsClosed = 0;
        for (var ch : s.toCharArray()) {
            if (ch == '(') {
                open++;
            }
            if (ch == '*') {
                if (open > 0) {
                    open--;
                    astAsClosed++;
                } else {
                    ast++;
                }
            }
            if (ch == ')') {
                if (open <= 0 && astAsClosed > 0) {
                    astAsClosed--;
                    ast++;
                } else {
                    open--;
                }
            }

            if (open < 0) {
                if (ast > 0) {
                    ast--;
                    open++;
                } else {
                    return false;
                }
            }
        }
        return open == 0;
    }
}

class SolutionWithStack { // also greedy approach, looking for the balance every step, with 2 stacks
    public boolean checkValidString(String s) {
        Deque<Integer> nonClosed = new LinkedList<>(); // storing index of char
        Deque<Integer> asterisk = new LinkedList<>();
        for (int i = 0; i < s.length(); i++){
            var ch = s.charAt(i);
            if (ch == '('){
                nonClosed.push(i);
            } else if (ch == '*'){
                asterisk.push(i);
            } else if (ch == ')'){
                if (!nonClosed.isEmpty()){ // try to close closest nonClosed at first
                    nonClosed.pop();
                } else if (!asterisk.isEmpty()) {
                    asterisk.pop();
                } else {
                    return false;
                }
            }
        }
        while (!nonClosed.isEmpty() && !asterisk.isEmpty()){ // balancing nonClosed with asterisk, which was after
            int nonCloseInd = nonClosed.pop();
            int asterInd = asterisk.pop();
            if (nonCloseInd > asterInd){
                return false; // means there is at least one open nonClosed (unbalanced) bracket
            }
        }
        return nonClosed.isEmpty();
    }
}

class SolutionGreedyOptim { // Greedy, always try to balance number of open vs closed, use potential min-max count of open braces
    public boolean checkValidString(String s) {
        int openMin = 0;
        int openMax = 0;
        for (var ch : s.toCharArray()) {
            if (ch == '(') {
                openMin++;
                openMax++;
            }
            if (ch == '*') {
                openMax++;
                openMin--;
            }
            if (ch == ')') {
                openMax--;
                openMin--;
            }

            if (openMax < 0){
                return false;
            }

            openMin = Math.max(openMin, 0); // !! important place
        }
        return openMin == 0;
    }
}

class Check{
    public static void main(String[] args) {
//        var res = new Solution().checkValidString("*(*)(*))((*)*)))(*)())*())()(()*)*)****)())(()()*(*(*())()((())))*()****)(*(()))((*()*(**(*()*)*()");
        var res = new Solution().checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()");
        var p = 0;
    }
}
