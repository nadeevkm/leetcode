class Solution {
    // if after diget came non dgit that digit -> 0
    // after leading zeroes every non digit ch -> 0
    // so overall we can see it a state transitions, down below valid input is written () is described as possibly spaces, than possibly -1 or + ...
    // ( )(-/+)(0)(123456789)....
    // all other inputs are not valid thus make '0'
    public int myAtoi(String s) {
        boolean trimmed = false;
        boolean signParsed = false;
        boolean leadZeroParsed = false;
        int sign = 1;
        long value = 0;
        for (var c : s.toCharArray()) {
            if (!trimmed) { // i.e. trimming state
                if (c == ' ') {
                    continue;
                } else if (c == '+' || c == '-') {
                    sign = c == '-' ? -1 : 1;
                    trimmed = true;
                    signParsed = true;
                } else if (c == '0') {
                    trimmed = true;
                    signParsed = true;
                    continue;
                } else if (Character.isDigit(c)) {
                    trimmed = true;
                    signParsed = true;
                    leadZeroParsed = true;
                    value = value * 10 + (c - '0');
                } else {
                    return 0;
                }
            } else if (!signParsed) { // i.e. sign parsing state
                if (c == '+' || c == '-') {
                    sign = c == '-' ? -1 : 1;
                    signParsed = true;
                } else if (c == '0') {
                    signParsed = true;
                    continue;
                } else if (Character.isDigit(c)) {
                    signParsed = true;
                    leadZeroParsed = true;
                    value = value * 10 + (c - '0');
                } else {
                    return 0;
                }
            } else if (!leadZeroParsed) { // i.e. lead zeroes parsing state
                if (c == '0') {
                    continue;
                } else if (Character.isDigit(c)) {
                    leadZeroParsed = true;
                    value = value * 10 + (c - '0');
                } else {
                    return 0;
                }
            } else { // i.e. integer parsing state
                if (Character.isDigit(c)) {
                    value = value * 10 + (c - '0');
                    if (value > Integer.MAX_VALUE) { // check for overflow
                        return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                    }
                } else {
                    break;
                }
            }
        }
        return sign * (int) value;
    }

    // possibly easier/cleanier do it with enum
    // enum State {TRIMMING, SIGN_PARSING, LEAD_ZEROES_PARSING, INTEGER_PARSING}
}

class SolutionCleaner {
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
                    if (res > Integer.MAX_VALUE){
                        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    }
                    p++;
                } else {
                    break;
                }
            }
        }
        res = sign * res;
        return (int) res;
    }

    enum State {
        WHITESPACES, SIGN, NUMBER
    }
}
