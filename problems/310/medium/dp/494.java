class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxSum += nums[i]; // or -> int maxSum = Arrays.stream(nums).sum();
        }

        if (maxSum < target || -maxSum > target) {
            return 0;
        }

        int m = 2 * maxSum + 1;
        int[][] mem = new int[n][m];
        int mid = (2 * maxSum + 1) / 2;
        mem[0][-nums[0] + mid] += 1;
        mem[0][nums[0] + mid] += 1;  //!!! not just =, cause in special case [0], target 0, there should be 2 at the 0

        for (int r = 1; r < n; r++) {
            for (int c = 0; c < m; c++) {
                // int curr = c - mid;
                // int prevLeft = curr - nums[r] + mid < 0 ? 0 : mem[r-1][curr - nums[r] + mid];
                // int prevRight = curr + nums[r] + mid >= m ? 0 : mem[r-1][curr + nums[r] + mid];
                int prevLeft = c - nums[r] < 0 ? 0 : mem[r - 1][c - nums[r]];
                int prevRight = c + nums[r] >= m ? 0 : mem[r - 1][c + nums[r]];
                mem[r][c] = prevLeft + prevRight;
            }
        }

        return mem[n - 1][target + mid];
    }
}

/*

            0  1  2  3  4 5 6 7 8 9 10 
           -5 -4 -3 -2 -1 0 1 2 3 4 5
1         | 0  0  0  0  1 0 1
1,1       |          1    2   1
1,1,1     |       1     1   3 0 1   0
1,1,1,1   |                   4   1
1,1,1,1,1 | 1                    x  1


*/