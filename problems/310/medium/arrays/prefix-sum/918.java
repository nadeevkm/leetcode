class SolutionPrefixSum {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        // 1st case - sum is inside array, simple Kadans' algo
        int totalMaxSum = nums[0];
        int currMaxSum = nums[0];
        for (int i = 1; i < n; i++) {
            currMaxSum = Math.max(currMaxSum + nums[i], nums[i]);
            totalMaxSum = Math.max(totalMaxSum, currMaxSum);
        }

        // 2st case - max sum is cicular, so it is maxsum from left + maxsum from right (no overlap)
        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];

        //calculate prefix sums from left and from righ
        for (int i = 1; i <= n - 1; i++) {
            left[i] = left[i - 1] + nums[i];
            right[n - 1 - i] = right[n - i] + nums[n - 1 - i];
        }
        //replace it with the biggest sums so far from the left and from the right
        for (int i = 1; i <= n - 1; i++) {
            left[i] = Math.max(left[i], left[i - 1]);
            right[n - 1 - i] = Math.max(right[n - 1 - i], right[n - i]);
        }
        // max sum is the max of possible non overlapped max sums from left and from right
        int specSum = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            specSum = Math.max(specSum, left[i] + right[i + 1]);
        }

        // resulted max is the biggest of two
        return Math.max(specSum, totalMaxSum);
    }
}

// if maxSum includes start end end circularly, than maxSum = totalSum - minSum
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        int maxSum = nums[0];
        int currMaxSum = nums[0];
        int minSum = nums[0];
        int currMinSum = nums[0];
        int total = nums[0];

        for (int i = 1; i < n; i++){
            currMaxSum = Math.max(currMaxSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currMaxSum);

            currMinSum = Math.min(currMinSum + nums[i], nums[i]);
            minSum = Math.min(minSum, currMinSum);

            total += nums[i];
        }

        return maxSum <= 0 ? maxSum : Math.max(maxSum, total - minSum);
        // or equal to ---> return total == minSum ? maxSum : Math.max(maxSum, total - minSum);
    }
}

class Check{
    public static void main(String[] args) {
        int res = new Solution().maxSubarraySumCircular(new int[]{5, -3, 5});
        int pp = 2;
    }
}