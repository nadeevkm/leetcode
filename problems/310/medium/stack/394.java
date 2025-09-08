// Input: s = "qq3[a2[c]]ll"
// Output: "qqaccaccaccll"

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class Solution { // recursion, return pair of values
    public String decodeString(String s) {
        var res = decode(s, 0);
        return ((StringBuilder) res[0]).toString();
    }

    public Object[] decode(String s, int p) {
        int i = p;
        var sb = new StringBuilder();
        for (; i < s.length(); i++) { // !! don't forget that i++ happens every time, before entering the block (so,)
            var ch = s.charAt(i);
            if (ch == '[') {
                continue;
            }
            if (ch == ']') {
                break;
            }
            if (Character.isDigit(ch)) {
                int repeat = 0;
                while (Character.isDigit(ch)) { // can be not only digits 1,2.., but a 257 for example
                    repeat = 10 * repeat + (ch - '0');
                    i++;
                    ch = s.charAt(i);
                }
                var res = decode(s, i);
                var decodedSb = (StringBuilder) res[0];
                var processedInd = (int) res[1];
                while (repeat-- > 0) {
                    sb.append(decodedSb);
                }
                i = processedInd;
                continue;
            }
            sb.append(ch);
        }
        return new Object[]{sb, i};
    }
}

class SolutionImproved { // recursion, we can avoid the need of using index as a part of returning pair by using a Queue, thus return only SB
    public String decodeString(String s) {
        Queue<Character> charQ = new LinkedList<>();
        for (var ch : s.toCharArray()){
            charQ.add(ch);
        }
        var decoded = decode(s, charQ);
        return decoded.toString();
    }

    public StringBuilder decode(String s, Queue<Character> q) {
        var sb = new StringBuilder();
        while (!q.isEmpty()) {
            var ch = q.poll();
            if (ch == '[') {
                continue;
            }
            if (ch == ']') {
                break;
            }
            if (Character.isDigit(ch)) {
                int repeat = 0;
                while (Character.isDigit(ch)) { // can be not only digits 1,2.., but a 257 for example
                    repeat = 10 * repeat + (ch - '0');
                    ch = q.poll(); // can safely do it without checking q.size() cause by problem statement after digit will always be smth
                }
                var decoded = decode(s, q);
                while (repeat-- > 0) {
                    sb.append(decoded);
                }
                continue;
            }
            sb.append(ch);
        }
        return sb;
    }
}

class SolutionStack { // two stacks for repeat times and for intermediate res
    public String decodeString(String s) {
        var res = new StringBuilder();
        Deque<StringBuilder> sbStack = new LinkedList<>();
        Deque<Integer> repStack = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            var ch = s.charAt(i);
            if (Character.isDigit(ch)){
                int repeat = 0;
                while (Character.isDigit(ch)){
                    repeat = 10*repeat + (ch - '0');
                    i++;
                    ch = s.charAt(i);
                }
                repStack.push(repeat);
            }
            if (ch == '['){
                sbStack.push(res);
                res = new StringBuilder();
            } else if (ch == ']'){
                var rep = repStack.pop();
                var prefix = sbStack.pop();
                var tmp = new StringBuilder(prefix);
                while (rep-- > 0){
                    tmp.append(res);
                }
                res = tmp;
            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }
}


class SolutionOneStack {
    public String decodeString(String s) {
        Deque<String> stack = new LinkedList<String>();
        var curr = new StringBuilder();
        int i = s.length() - 1;
        while(i >= 0){
            if (s.charAt(i) == ']'){
                stack.push(curr.toString());
                curr = new StringBuilder();
                i--;
            } else if (s.charAt(i) == '[') {
                i--;
            } else if (Character.isDigit(s.charAt(i))){
                int times = 0;
                int base = 1;
                while (i >= 0 && Character.isDigit(s.charAt(i))){
                    times = base*(s.charAt(i) - '0') + times;
                    base = base*10;
                    i--;
                }
                var chunk = curr.toString();
                curr = new StringBuilder();
                while (times-- > 0){
                    curr.append(chunk);
                }
                curr.append(stack.pop());
            } else { // char is alphabet character
                curr.insert(0, s.charAt(i));
                i--;
            }
        }
        return curr.toString();
    }
}

class Check {
    public static void main(String[] args) {
        var res1 = new Solution().decodeString("a3[d]4[q]");
        var res2 = new Solution().decodeString("qq3[a2[c]]ll");
        var res3 = new Solution().decodeString("2[abc]3[cd]ef");
        var r = 0;
    }
}