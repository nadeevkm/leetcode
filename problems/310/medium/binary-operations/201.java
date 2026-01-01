class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int res = 0;
        for (int i = 31; i >= 0; i--){
            res = res << 1;
            var noZeroes = checkNoZeroesBetweenDigits(left, right, i);
            res += noZeroes ? 1 : 0;
        }
        return res;
    }

    private boolean checkNoZeroesBetweenDigits(int left, int right, int i){
        var leftShift = left >> i;
        var rightShift = right >> i;
        if ((leftShift & 1) == 0 || (rightShift & 1 )== 0 ){ // if one digit or another is 0
            return false;
        }

        // if both are 1, then no zeroes only when two numbers have the same number of digits in binary format (1_1_000,1_1_001,1_1_010...)
        // if right had more digits, than it would be rollover of the lower digits and a transition of 1 -> 0 in i-th position/place somewhere between, so there would be 0
        leftShift = leftShift >> 1;
        rightShift = rightShift >> 1;
        return leftShift == rightShift;
    }
}

class Check{
    public static void main(String[] args) {
        new Solution().rangeBitwiseAnd(5,7);
    }
}

// 32 31 ... 2 1

// 0000
// 0001
// 0010
// 0011
// 0100
// 0101    5
// 0110
// 0111    7
// 1000    8

// 0100

