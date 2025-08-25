class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int[] leftsum = new int[n];
        leftsum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftsum[i] = leftsum[i - 1] + nums[i];
        }
        int[] rightsum = new int[n];
        rightsum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightsum[i] = rightsum[i + 1] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            int ls = (i == 0) ? 0 : leftsum[i - 1];
            int rs = (i == n - 1) ? 0 : rightsum[i + 1];
            if (ls == rs) {
                return i;
            }
        }
        return -1;
    }
}

class SolutionOpt { // no need to create arrays - we can calculate total sum and and then rightrunningsum = total - leftrunningsum - nums[i]
    public int pivotIndex(int[] nums) {
        int leftsum = 0;
        int totalsum = 0;
        for (int n : nums){
            totalsum += n;
        }
        for (int i = 0; i < nums.length; i++) {
            // rightsum should be equal to left, but rightsum = total - leftsum
            if (leftsum == totalsum - nums[i] - leftsum){
                return i;
            }
            leftsum += nums[i];
        }
        return -1;
    }
}