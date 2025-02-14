
class Solution1 {
    // represent
    public int singleNumber(int[] nums) {
        int a = 0;
        int b = 0;
        for (int n : nums){
            int bitmask = 1;
            for (int i = 0; i < 32; i++){
                int bit = n & bitmask;
                bitmask = bitmask << 1;

                if (bit == 0){
                    continue;
                }

                if ((bit & a) == 0){
                    a = a + bit;
                } else {
                    if ((bit & b) == 0){
                        b = b + bit;
                    } else {
                        a = a ^ bit;
                        b = b ^ bit;
                    }
                }
            }
        }
        return a;
    }
}

class Solution2 {
    public int singleNumber(int[] nums) {  // consequently count sum of one particular bit from all numbers, if it more that %3, that means this is a bit from seeked number
        int ans = 0;
        for (int i = 0; i < 32; i++){
            int bitSum = 0;
            for (int n : nums) {
                if (((n >> i) & 1) == 1) {
                    bitSum++;
                    bitSum = bitSum % 3;
                }
            }
            if (bitSum != 0) {
                ans = ans + (bitSum << i);
            }
        }
        return ans;
    }
}


class Solution3 { // idea is to have state machine/state counter with 3 states ( which is represented by two bits - 00, 01, 11 and changed by next appeared bit)
    /*
    a b num  -> a b
    0 0  0   -> 0 0
    0 1  0   -> 0 1
    1 0  0   -> 1 0
    0 0  1   -> 0 1
    0 1  1   -> 1 0
    1 0  1   -> 0 0
     */
    public int singleNumber(int[] nums){
        int a = 0;
        int b = 0;
        for (int n : nums){
            b = (b ^ n) & ~a;
            a = (a ^ n) & ~b;
        }
        return b;
    }
}

class Check {

    public static void main(String[] args) {
        var nums =  new int [] {0,1,0,1,0,1,99};
        var res = new Solution3().singleNumber(nums);
        int pp = 2;
    }
}
