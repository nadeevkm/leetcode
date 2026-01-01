class Solution {
    public int myAtoi(String s) {
        long res = 0;
        int sign = 1;
        State st = State.WHITESPACES;
        int p = 0;
        while (p < s.length()) {
            char ch = s.charAt(p);
            if (st == State.WHITESPACES) {
                if (ch == ' ') {
                    p++;
                } else if (ch == '-' || ch == '+') {
                    st = State.SIGN;
                } else if (Character.isDigit(ch)) {
                    st = State.NUMBER;
                } else {
                    break;
                }
            } else if (st == State.SIGN) {
                if (ch == '-' || ch == '+') {
                    sign = ch == '+' ? 1 : -1;
                    st = State.NUMBER;
                    p++;
                } else {
                    break;
                }
            } else { // st == State.NUMBER;
                if (Character.isDigit(ch)) {
                    res = res * 10 + (ch - '0');
                    p++;
                } else {
                    break;
                }
            }
        }
        res = sign * res;
        if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) res;
    }

    enum State {
        WHITESPACES, SIGN, NUMBER
    }
}
