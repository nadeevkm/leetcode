class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;

        int[] presums = new int[nums.length];
        presums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            presums[i] = presums[i - 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            int targetSum = i == 0 ? target : target + presums[i - 1];
            int l = i;
            int r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (presums[m] >= targetSum) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            minLen = Math.min(minLen, l - i + 1);
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}