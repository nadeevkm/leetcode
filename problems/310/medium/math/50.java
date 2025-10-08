class SolutionRecur { // idea is from here // https://cp-algorithms.com/algebra/binary-exp.html
    // however it doesn't pass last testes because of StackOverflow, so need to use iterative
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double res = pow(x, Math.abs(n));
        return (n > 1) ? res : 1 / res;
    }

    public double pow(double x, int n) {
        if (n == 1) {
            return x;
        } else {
            if (n % 2 == 0) {
                var val = pow(x, n / 2);
                return val * val;
            } else {
                var val = pow(x, (n - 1) / 2);
                return val * val * x;
            }
        }
    }
}

// explanation:
// x = 5
// n = 13
// as 13 = 1101 = 8 + 4 + 1
// 5^13 = 5^(1101)=5^8 x 5^4 x _ x 5^1
// so we multiply x by itself every shift of 1101 >>
// getting 5^1, 5^2, 5^4, 5^8..
// but we include it in res only if last right bit from 1101 == 1

class SolutionIter {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double res = pow(x, Math.abs((long) n));
        return (n > 1) ? res : 1 / res;
    }

    public double pow(double x, int n) {
        double res = 1;
        while (n > 0) {
            if ((n & 1) == 1) { // analog n % 2
                res = res * x;
            }
            x = x * x;
            n = n >> 1;
        }
        return res;
    }
}