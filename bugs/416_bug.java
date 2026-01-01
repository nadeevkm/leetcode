class Solution {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int targetSum = sum / 2;

        boolean[][] mem = new boolean[nums.length][targetSum + 1]; // can improve to two rows only (prev, current)
        mem[0][0] = true; // for 0 sum we can take nothing
        mem[0][nums[0]] = true;

        for (int c = 0; c <= targetSum; c++) {
            for (int r = 1; r < nums.length; r++) {
                var without = mem[r - 1][c];
                var with = (c - nums[r]) < 0 ? false : mem[r - 1][c - nums[r]];
                mem[r][c] = without || with;
            }
        }

        return mem[nums.length - 1][targetSum];
    }
}

/*
          0. 1. 2. 3. 4. 5 ...   11 ... 21
[]        t. t. t. t. t. t
1         0  t  0
1 5.      0. t  0. 0  0  t ....  0 ...
1 5 11.   0. t           t       t
1 5 11 5  0.

*/