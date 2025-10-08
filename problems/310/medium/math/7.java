class Solution {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int mod = x % 10;
            int rem = x / 10;
            if (rem == 0) {
                if ((res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && mod > Integer.MAX_VALUE % 10)) ||
                        (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && mod < Integer.MIN_VALUE % 10))) {
                    return 0;
                }
            }
            res = res * 10 + mod;
            x = rem;
        }
        return res;
    }
}

class SolutionConcise {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int mod = x % 10;
            x = x / 10;
            int backUp = res;
            res = res * 10 + mod;
            if ((res - mod) / 10 != backUp) { // means was an overflow
                return 0;
            }
        }
        return res;
    }
}


class SolutionRepetit {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int sign = Integer.signum(x);
        int value = Math.abs(x);

        int rev = 0;
        while (value != 0) {
            int rem = value % 10;
            value = value / 10;
            int newRev = rev * 10 + rem;
            if ((newRev - rem) / 10 != rev) {
                return 0;
            }
            rev = newRev;
        }

        int res = rev * sign;
        if (res * sign != rev) {
            return 0;
        }
        return res;
    }
}

class Check{
    public static void main(String[] args) {
        var res = new SolutionConcise().reverse(-123);
        var pp = 0;
    }
}