class Solution {
    public int getSum(int a, int b) {
        int rem = 0;
        int res = 0;
        for (int i = 0; i < 32; i++){
            int a1 = (a >> i) & 1;
            int b1 = (b >> i) & 1;
            int r1 = rem ^ a1 ^ b1;
            rem = (a1 & b1) | (rem & (a1 | b1));
            res += (r1 << i);
        }
        return res;
    }
}

class Check{
    public static void main(String[] args) {
        var r = new Solution().getSum(2, 5);
    }
}