class Solution {
    public int maxProduct(int[] nums) {
        int runningMax = nums[0];
        int runningMin = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++){
            int newRunningMax = Math.max(nums[i], Math.max(nums[i] * runningMax, nums[i] * runningMin));
            // cause if nums[i] is negative we can get max, by multiplying min. i.e. neg * neg = posit
            int newRunningMin = Math.min(nums[i], Math.min(nums[i] * runningMax, nums[i] * runningMin));
            // and thus we need to keep track of running min as well
            max = Math.max(max, newRunningMax);
            runningMax = newRunningMax;
            runningMin = newRunningMin;
        }
        return max;
    }
}