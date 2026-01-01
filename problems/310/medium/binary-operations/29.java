class Solution {
    public int divide1(int dividend, int divisor) {
        if (dividend == 0){
            return 0;
        }
        int overRes = 0;
        int divisorMem = divisor;
        int resSign = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0) ? 1 : -1;
        int res = 1;
        while (true){
            while ((divisor << 1) <= dividend){
                res = res << 1;
                divisor = divisor << 1;
            }
            if (dividend - divisor < divisorMem){
                overRes += res;
                break;
            }
            dividend = dividend - divisor;
            divisor = divisorMem;
            overRes += res;
            res = 1;
        }
        return resSign*overRes;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0){
            return 0;
        }
        int res = 0;
        int sign = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0) ? 1 : -1;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while (dividend >= divisor){
            int count = 0;
            while ((divisor << (count + 1)) <= dividend){
                count++;
            }
            dividend = dividend - (divisor << count);
            res += (int) Math.pow(2, count);
        }
        return sign*res;
    }
}

class Check{
    public static void main(String[] args) {
        //var res1 = new Solution().divide(10, 3);
        var res2 = new Solution().divide(-2147483648, -3);
    }



}